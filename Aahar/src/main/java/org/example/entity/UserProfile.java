package org.example.entity;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Table(name = "user_profiles")
@Getter
public class UserProfile {

    @Id @GeneratedValue private Long id;
    private String name;
    private String region;
    @Column(name = "food_preference")
    private String foodPreference;
    private int budget;

    public UserProfile() {}

    public UserProfile(String name, String region, String foodPreference, int budget) {
        this.name = name;
        this.region = region;
        this.foodPreference = foodPreference;
        this.budget = budget;
    }
}
