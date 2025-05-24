package org.example.ai;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

/**
 * Uses Hugging Face Inference API (google/flan-t5-small) to generate a meal plan.
 */
@Component
public class HFMealPlanGenerator implements MealPlanGenerator {

    @Autowired private WebClient hfClient;
    private static final ObjectMapper mapper = new ObjectMapper();

    @Override
    public MealPlanResponseDTO generate(MealPlanRequestDTO req) {
        String prompt = buildPrompt(req);

        // HF expects JSON: { "inputs": "your prompt here" }
        Mono<JsonNode> resp = hfClient.post()
                .bodyValue(mapper.createObjectNode().put("inputs", prompt))
                .retrieve()
                .bodyToMono(JsonNode.class);

        try {
            JsonNode first = resp.block().get(0).get("generated_text");
            return parseResponse(first.asText(), req);
        } catch (Exception e) {
            return defaultResponse(req);
        }
    }

    private String buildPrompt(MealPlanRequestDTO r) {
        StringBuilder sb = new StringBuilder();
        sb.append("Create a ").append(r.getFoodPreference() != null ? r.getFoodPreference() : "balanced")
                .append(" Indian healthy meal plan keeping the local cuisine in mind");
        if (r.getRegion() != null) sb.append(" for ").append(r.getRegion());
        if (r.getBudget() != null) sb.append(" under ₹").append(r.getBudget());
        sb.append(". Provide JSON: {\"breakfast\":...,\"lunch\":...,\"dinner\":...,\"estimatedBudget\":...}");
        return sb.toString();
    }

    private MealPlanResponseDTO parseResponse(String text, MealPlanRequestDTO r) {
        try {
            return mapper.readValue(text, MealPlanResponseDTO.class);
        } catch (Exception ex) {
            return defaultResponse(r);
        }
    }

    private MealPlanResponseDTO defaultResponse(MealPlanRequestDTO r) {
        MealPlanResponseDTO d = new MealPlanResponseDTO();
        d.setBreakfast("Oats & Fruits");
        d.setLunch("Khichdi");
        d.setDinner("Soup & Salad");
        d.setCost(r.getBudget() != null ? r.getBudget() : 100);
        return d;
    }
}
