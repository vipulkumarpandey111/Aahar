package org.example.ai;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.example.dto.MealPlanRequestDTO;
import org.example.dto.MealPlanResponseDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.time.Duration;

@Component
public class HFMealPlanGenerator implements MealPlanGenerator {

    private static final Logger logger = LoggerFactory.getLogger(HFMealPlanGenerator.class);
    @Autowired private WebClient hfClient;
    private static final ObjectMapper mapper = new ObjectMapper();

    @Override
    public MealPlanResponseDTO generate(MealPlanRequestDTO req) {
        String prompt = buildPrompt(req);
        ObjectNode requestBody = buildRequestBody(prompt);
        
        logger.info("Sending request to Hugging Face API with prompt: {}", prompt);

        try {
            JsonNode result = hfClient.post()
                    .bodyValue(requestBody)
                    .retrieve()
                    .onStatus(status -> status.is4xxClientError() || status.is5xxServerError(),
                            response -> response.bodyToMono(String.class)
                                    .flatMap(error -> {
                                        logger.error("API Error: {} - {}", response.statusCode(), error);
                                        return Mono.error(new RuntimeException(
                                                "API Error: " + response.statusCode() + " - " + error));
                                    }))
                    .bodyToMono(JsonNode.class)
                    .timeout(Duration.ofSeconds(30))
                    .block();

            if (result == null || !result.has("choices") || result.get("choices").size() == 0) {
                logger.error("Invalid response format from API");
                return defaultResponse(req);
            }

            JsonNode message = result.get("choices").get(0).get("message");
            if (message == null || !message.has("content")) {
                logger.error("Missing content in API response");
                return defaultResponse(req);
            }

            MealPlanResponseDTO mealResponse = parseResponse(message.get("content").asText(), req);
            return mealResponse;
        } catch (Exception e) {
            logger.error("Error generating meal plan", e);
            return defaultResponse(req);
        }
    }

    private ObjectNode buildRequestBody(String prompt) {
        ObjectNode messageNode = mapper.createObjectNode();
        messageNode.put("role", "user");
        messageNode.put("content", prompt);

        ArrayNode messagesArray = mapper.createArrayNode();
        messagesArray.add(messageNode);

        ObjectNode requestBody = mapper.createObjectNode();
        requestBody.set("messages", messagesArray);
        requestBody.put("model", "Meta-Llama-3.1-8B-Instruct");
        requestBody.put("stream", false);
        
        return requestBody;
    }

    private String buildPrompt(MealPlanRequestDTO r) {
        StringBuilder sb = new StringBuilder();
        sb.append("Generate a 1 day ");
        if (r.getFoodPreference() != null) {
            sb.append(r.getFoodPreference()).append(" ");
        }
        sb.append("healthy meal plan");
        if (r.getRegion() != null) {
            sb.append(" for ").append(r.getRegion());
        }
        sb.append(" based on local cuisine");
        if (r.getBudget() != null) {
            sb.append(" within budget ₹").append(r.getBudget());
        }
        sb.append(". Return only a JSON response in this format: {\"breakfast\": \"meal description\", \"lunch\": \"meal description\", \"dinner\": \"meal description\", \"estimatedBudget\": number}");
        return sb.toString();
    }

    private MealPlanResponseDTO parseResponse(String text, MealPlanRequestDTO r) {
        try {
            // Extract JSON from the response text
            int startIdx = text.indexOf("{");
            int endIdx = text.lastIndexOf("}") + 1;
            if (startIdx >= 0 && endIdx > startIdx) {
                String jsonStr = text.substring(startIdx, endIdx);
                MealPlanResponseDTO mapperDTO = mapper.readValue(jsonStr, MealPlanResponseDTO.class);
                return mapperDTO;
            }
            throw new Exception("No valid JSON found in response");
        } catch (Exception ex) {
            logger.error("Failed to parse API response: {}", text, ex);
            return defaultResponse(r);
        }
    }

    private MealPlanResponseDTO defaultResponse(MealPlanRequestDTO r) {
        MealPlanResponseDTO d = new MealPlanResponseDTO();
        d.setBreakfast("Oats & Fruits");
        d.setLunch("Khichdi");
        d.setDinner("Soup & Salad");
        d.setEstimatedBudget(r.getBudget() != null ? r.getBudget() : 100);
        return d;
    }
}