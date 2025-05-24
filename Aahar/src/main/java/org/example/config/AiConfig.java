package org.example.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

import java.time.Duration;

@Configuration
public class AiConfig {
    @Value("${huggingface.api.token}")
    private String hfToken;

    @Bean
    public WebClient hfClient() {
        return WebClient.builder()
                .baseUrl("https://api-inference.huggingface.co/models/google/flan-t5-small")
                .defaultHeader("Authorization", "Bearer " + hfToken)
                .build();
    }
}
