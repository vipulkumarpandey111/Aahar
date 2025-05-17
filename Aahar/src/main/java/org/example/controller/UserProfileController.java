package org.example.controller;

import jakarta.validation.Valid;
import org.example.dto.UserProfileRequestDTO;
import org.example.entity.MealPlan;
import org.example.service.UserProfileServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class UserProfileController {

    @Autowired private UserProfileServiceImpl service;

    @PostMapping("/users/create-user")
    public MealPlan createAndSuggest(@Valid @RequestBody UserProfileRequestDTO dto) {
        MealPlan mealPlan = service.createUserWithMealSuggestion(dto);

        if (mealPlan == null) {
            throw new RuntimeException("Meal plan could not be created");
        }
        return mealPlan;
    }
}
