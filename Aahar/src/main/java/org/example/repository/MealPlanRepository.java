package org.example.repository;

import org.example.entity.MealPlan;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MealPlanRepository extends JpaRepository<MealPlan, Long> {
    Optional<MealPlan> findFirstByRegionAndFoodPreference(String region, String foodPreference);
    Optional<MealPlan> findByUserId(Long userId);
}
