package org.example.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "meal_plans")
@Getter
@Setter
public class MealPlan {
    @Id
    @GeneratedValue
    private Long id;
    private String region;
    @Column(name = "food_preference")
    private String foodPreference;
    private String breakfast;
    private String lunch;
    private String dinner;
    @Column(name = "suggested_budget")
    private int suggestedBudget;

    public MealPlan() {}

    public MealPlan(String region, String food_preference, String breakfast, String lunch, String dinner, int suggested_budget) {
        this.region = region;
        this.foodPreference = food_preference;
        this.breakfast = breakfast;
        this.lunch = lunch;
        this.dinner = dinner;
        this.suggestedBudget = suggested_budget;
    }
}