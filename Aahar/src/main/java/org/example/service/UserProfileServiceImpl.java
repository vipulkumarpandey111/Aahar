package org.example.service;

import org.example.dto.*;
import org.example.entity.UserProfile;
import org.example.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserProfileServiceImpl implements UserProfileService {

    @Autowired private UserProfileRepository userRepo;
    @Autowired private MealPlanRepository mealRepo;

    @Override
    public UserProfile createUser(UserProfileRequestDTO dto) {
        UserProfile user = new UserProfile(dto.getName(), dto.getRegion(), dto.getFoodPreference(), dto.getBudget()); // Set fields from DTO
        userRepo.save(user);
        return user;
    }
}
