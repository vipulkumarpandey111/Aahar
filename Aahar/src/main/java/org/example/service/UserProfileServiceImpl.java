package org.example.service;

import org.example.dto.UserProfileRequestDTO;
import org.example.entity.MealPlan;
import org.example.entity.UserProfile;
import org.example.repository.MealPlanRepository;
import org.example.repository.UserProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserProfileServiceImpl implements UserProfileService {

    @Autowired private UserProfileRepository userRepo;
    @Autowired private MealPlanRepository mealRepo;

    @Override
    public MealPlan createUserWithMealSuggestion(UserProfileRequestDTO dto) {
        UserProfile user = new UserProfile(dto.getName(), dto.getRegion(), dto.getFoodPreference(), dto.getBudget()); // Set fields from DTO
        userRepo.save(user);

        //MealPlan planTemplate = mealRepo.findFirstByRegionAndFoodPreference(dto.getRegion(), dto.getFoodPreference())
               // .orElseThrow(() -> new RuntimeException("No meal plan template found"));

        MealPlan mealPlan = new MealPlan(
                user.getRegion(),                     // region
                user.getFoodPreference(),            // foodPreference
                "Poha with sprouts",                 // breakfast
                "Dal, Rice, and Mix Veg",            // lunch
                "Multigrain Roti with Sabzi",        // dinner
                60                             // Link to UserProfile
        );

        mealPlan.setUser(user);
        mealRepo.save(mealPlan);

        return mealPlan;
    }
}
