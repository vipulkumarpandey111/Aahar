package org.example.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
public class MealPlanResponseDTO {
    private Long planId;
    private String breakfast;
    private String lunch;
    private String dinner;
    private int cost;
    private LocalDateTime generatedAt;
    private Long userId;
    private String region;
    private String foodPreference;
}
