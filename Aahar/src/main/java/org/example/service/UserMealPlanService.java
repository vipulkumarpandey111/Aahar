package org.example.service;

import jakarta.persistence.*;
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

    @Autowired
    private MealPlanRepository mealRepo;
    @Autowired private UserProfileRepository userRepo;
    @Autowired private UserMealPlanRepository userMealRepo;

    private static final MealPlan DEFAULT_PLAN = createDefaultPlan();

    public MealPlanResponseDTO generateAndSave(MealPlanRequestDTO req) {
        // 1. Fetch user
        UserProfile user = userRepo.findById(req.getUserId())
                .orElseThrow(() -> new EntityNotFoundException("User not found"));

        // 2. Filter master meal plans
        List<MealPlan> candidates = mealRepo.findByRegionAndFoodPreference(
                req.getRegion(), req.getFoodPreference());

        // 3. Budget filter
        MealPlan selected = candidates.stream()
                .filter(p -> req.getBudget() == null || p.getSuggestedBudget() <= req.getBudget())
                .findAny()
                .orElse(DEFAULT_PLAN);

        // 4. Save mapping
        UserMealPlan ump = new UserMealPlan(user, selected);
        userMealRepo.save(ump);

        // 5. Return DTO
        MealPlanResponseDTO resp = UserMealPlanResponseMapper.toDto(ump);
        return resp;
    }

    private static MealPlan createDefaultPlan() {
        MealPlan p = new MealPlan();
        p.setRegion("Default");
        p.setFoodPreference("Veg");
        p.setBreakfast("Oats & Fruits");
        p.setLunch("Khichdi");
        p.setDinner("Soup & Salad");
        p.setSuggestedBudget(50);
        return p;
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

