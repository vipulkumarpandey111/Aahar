package org.example.dto;

import lombok.*;

@Getter
@Setter
public class MealPlanResponseDTO {
    private Long id;
    private String region;
    private String foodPreference;
    private String breakfast;
    private String lunch;
    private String dinner;
    private int cost;
    private Long userId;
}
