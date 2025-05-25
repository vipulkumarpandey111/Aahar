package org.example.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class AiConfig {
    @Value("${huggingface.api.token}")
    private String hfToken;

    @Bean
    public WebClient hfClient() {
        return WebClient.builder()
                .baseUrl("https://router.huggingface.co/sambanova/v1/chat/completions")
                .defaultHeader("Authorization", "Bearer " + hfToken)
                .defaultHeader("Content-Type", "application/json")
                .build();
    }
}