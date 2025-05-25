package org.example.service;

import jakarta.persistence.*;
import org.springframework.cache.annotation.Cacheable;
import org.example.ai.MealPlanGenerator;
import org.example.dto.*;
import org.example.entity.*;
import org.example.mapper.UserMealPlanResponseMapper;
import org.example.repository.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserMealPlanService {

    @Autowired private MealPlanGenerator generator;
    @Autowired private MealPlanRepository mealRepo;
    @Autowired private UserProfileRepository userRepo;
    @Autowired private UserMealPlanRepository userMealRepo;

    @Cacheable(value = "aiMealPlans", key = "#req.region+'|'+#req.foodPreference+'|'+#req.budget")
    public MealPlanResponseDTO generateAndSave(MealPlanRequestDTO req) {
        UserProfile user = userRepo.findById(req.getUserId())
                .orElseThrow(() -> new EntityNotFoundException("User not found"));

        // 1. Try AI generation
        MealPlanResponseDTO aiResponse = generator.generate(req);

        MealPlanResponseDTO finalPlan;
        boolean fromAI = (aiResponse != null
                && aiResponse.getBreakfast() != null
                && aiResponse.getLunch()    != null
                && aiResponse.getDinner()   != null);

        if (fromAI) {
            finalPlan = aiResponse;
            // 2a. Backup new AI-generated plan into meal_plans
            MealPlan backup = new MealPlan();
            backup.setRegion(req.getRegion());
            backup.setFoodPreference(req.getFoodPreference());
            backup.setBreakfast(aiResponse.getBreakfast());
            backup.setLunch(aiResponse.getLunch());
            backup.setDinner(aiResponse.getDinner());
            backup.setSuggestedBudget(aiResponse.getEstimatedBudget());
            mealRepo.save(backup);
        } else {
            // 2b. Fallback to static DB lookup
            List<MealPlan> list = mealRepo
                    .findByRegionAndFoodPreference(req.getRegion(), req.getFoodPreference());
            if (!list.isEmpty()) {
                MealPlan mp = list.get(0);
                finalPlan = new MealPlanResponseDTO();
                finalPlan.setBreakfast(mp.getBreakfast());
                finalPlan.setLunch(mp.getLunch());
                finalPlan.setDinner(mp.getDinner());
                finalPlan.setEstimatedBudget(mp.getSuggestedBudget());
            } else {
                // Last-resort default
                finalPlan = defaultResponse(req);
            }
        }

        // 3. Persist per-user record with embedded fields
        UserMealPlan ump = new UserMealPlan(
                user,
                finalPlan.getBreakfast(),
                finalPlan.getLunch(),
                finalPlan.getDinner(),
                finalPlan.getEstimatedBudget()
        );
        userMealRepo.save(ump);

        return finalPlan;
    }

    private MealPlanResponseDTO defaultResponse(MealPlanRequestDTO r) {
        MealPlanResponseDTO d = new MealPlanResponseDTO();
        d.setBreakfast("Oats & Fruits");
        d.setLunch("Khichdi");
        d.setDinner("Soup & Salad");
        d.setEstimatedBudget(r.getBudget() != null ? r.getBudget() : 100);
        return d;
    }

    public List<MealPlanResponseDTO> findByUser(Long userId) {
        // Verify user exists
        UserProfile user = userRepo.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));

        // Fetch all UserMealPlan records for this user
        List<UserMealPlan> records = userMealRepo.findByUserId(userId);

        // Map to DTO
        return records.stream()
                .map(ump -> {
                    MealPlanResponseDTO dto = UserMealPlanResponseMapper.toDto(ump);
                    return dto;
                })
                .collect(Collectors.toList());
    }
}

