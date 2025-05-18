package org.example.dto;

import lombok.*;

@Getter
@Setter
public class MealPlanRequestDTO {
    private Long userId;             // optional
    private String region;           // optional
    private String foodPreference;   // optional
    private Integer budget;
}
