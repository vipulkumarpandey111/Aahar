package org.example.mapper;

import org.example.dto.MealPlanResponseDTO;
import org.example.entity.MealPlan;
import org.example.entity.UserMealPlan;

public class UserMealPlanResponseMapper {
    public static MealPlanResponseDTO toDto(UserMealPlan userMealPlan) {
        MealPlanResponseDTO dto = new MealPlanResponseDTO();
        MealPlan mealPlan = userMealPlan.getMealPlan();
        dto.setPlanId(userMealPlan.getId());
        dto.setMealId(mealPlan.getId());
        dto.setRegion(mealPlan.getRegion());
        dto.setFoodPreference(mealPlan.getFoodPreference());
        dto.setBreakfast(mealPlan.getBreakfast());
        dto.setLunch(mealPlan.getLunch());
        dto.setDinner(mealPlan.getDinner());
        dto.setCost(mealPlan.getSuggestedBudget());
        dto.setUserId(userMealPlan.getUser().getId());
        // Optionally map generatedAt or other fields if needed
        return dto;
    }
}
