package org.example.controller;

import jakarta.validation.Valid;
import org.example.dto.UserProfileRequestDTO;
import org.example.entity.UserProfile;
import org.example.repository.UserProfileRepository;
import org.example.service.UserProfileServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserProfileController {

    @Autowired private UserProfileServiceImpl service;
    @Autowired private UserProfileRepository userProfileRepository;

    @PostMapping("/create-user")
    public UserProfile createAndSuggest(@Valid @RequestBody UserProfileRequestDTO dto) {
        UserProfile user = service.createUser(dto);
        return user;
    }
}
