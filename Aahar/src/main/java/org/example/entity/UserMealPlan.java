package org.example.entity;

import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDateTime;

@Entity
@Table(name = "user_meal_plans")
@Getter
public class UserMealPlan {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id")
    private UserProfile user;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "meal_plan_id")
    private MealPlan mealPlan;

    private LocalDateTime generatedAt = LocalDateTime.now();

    // Constructors
    public UserMealPlan() {}
    public UserMealPlan(UserProfile user, MealPlan mealPlan) {
        this.user = user;
        this.mealPlan = mealPlan;
    }

    // Getters/setters
}
