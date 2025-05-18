package org.example.repository;

import org.example.entity.UserMealPlan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public interface UserMealPlanRepository extends JpaRepository<UserMealPlan, Long> {
    List<UserMealPlan> findByUserId(Long userId);
}

