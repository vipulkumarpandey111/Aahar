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

    // Embedded meal details
    private String breakfast;
    private String lunch;
    private String dinner;
    private int suggestedBudget;

    private LocalDateTime generatedAt = LocalDateTime.now();

    public UserMealPlan() {}

    public UserMealPlan(UserProfile user,
                        String breakfast,
                        String lunch,
                        String dinner,
                        int suggestedBudget) {
        this.user = user;
        this.breakfast = breakfast;
        this.lunch = lunch;
        this.dinner = dinner;
        this.suggestedBudget = suggestedBudget;
    }
}
