package org.example.controller;

import org.example.dto.MealPlanResponseDTO;
import org.example.entity.MealPlan;
import org.example.repository.MealPlanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MealPlanController {

    @Autowired
    private MealPlanRepository repo;

    @GetMapping("/api/meal-plan/{userId}")
    public MealPlanResponseDTO getMealPlan(@PathVariable Long userId) {
        MealPlan mealPlan =  repo.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("Meal plan not found"));

        MealPlanResponseDTO dto = new MealPlanResponseDTO();
        dto.setId(mealPlan.getId());
        dto.setRegion(mealPlan.getRegion());
        dto.setFoodPreference(mealPlan.getFoodPreference());
        dto.setBreakfast(mealPlan.getBreakfast());
        dto.setLunch(mealPlan.getLunch());
        dto.setDinner(mealPlan.getDinner());
        dto.setCost(mealPlan.getSuggestedBudget());
        dto.setUserId(mealPlan.getUser().getId());

        return dto;
    }
}