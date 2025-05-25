package org.example.mapper;

import org.example.dto.*;
import org.example.entity.*;

public class UserMealPlanResponseMapper {
    public static MealPlanResponseDTO toDto(UserMealPlan mealPlan) {
        MealPlanResponseDTO dto = new MealPlanResponseDTO();
        dto.setPlanId(mealPlan.getId());
        dto.setBreakfast(mealPlan.getBreakfast());
        dto.setLunch(mealPlan.getLunch());
        dto.setDinner(mealPlan.getDinner());
        dto.setEstimatedBudget(mealPlan.getSuggestedBudget());
        dto.setUserId(mealPlan.getUser().getId());

        return dto;
    }
}
