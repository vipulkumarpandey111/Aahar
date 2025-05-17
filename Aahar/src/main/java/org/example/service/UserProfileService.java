package org.example.service;

import org.example.dto.UserProfileRequestDTO;
import org.example.entity.MealPlan;

public interface UserProfileService {
    MealPlan createUserWithMealSuggestion(UserProfileRequestDTO dto);
}

