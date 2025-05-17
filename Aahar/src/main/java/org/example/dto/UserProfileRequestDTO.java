package org.example.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.*;

@Getter
public class UserProfileRequestDTO {
    @NotBlank private String name;
    @NotBlank private String region;
    @NotBlank private String foodPreference;
    @Positive private int budget;
}
