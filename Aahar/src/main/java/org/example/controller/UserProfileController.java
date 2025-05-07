package org.example.controller;

import org.example.entity.UserProfile;
import org.example.repository.UserProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserProfileController {

    @Autowired
    private UserProfileRepository userProfileRepository;

    @PostMapping("/create")
    public UserProfile createUser() {
        UserProfile user = new UserProfile("Rahul", "Maharashtra", "Vegetarian");
        return userProfileRepository.save(user);
    }
}
