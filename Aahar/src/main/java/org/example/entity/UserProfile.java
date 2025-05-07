package org.example.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "user_profiles")
public class UserProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String region;

    private String foodPreference;

    // Constructors
    public UserProfile() {}

    public UserProfile(String name, String region, String foodPreference) {
        this.name = name;
        this.region = region;
        this.foodPreference = foodPreference;
    }

    // Getters and setters...
}
