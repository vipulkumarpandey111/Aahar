package org.example.controller;

import org.example.dto.*;
import org.example.entity.*;
import org.example.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/meal-plans")
public class UserMealPlanController {

    @Autowired private UserMealPlanService userMealPlanService;

    @PostMapping("/generate")
    public ResponseEntity<MealPlanResponseDTO> generate(
            @RequestBody MealPlanRequestDTO request) {
        MealPlanResponseDTO resp = userMealPlanService.generateAndSave(request);
        return ResponseEntity.ok(resp);
    }

    @GetMapping("/history/{userId}")
    public ResponseEntity<List<MealPlanResponseDTO>> history(
            @PathVariable Long userId) {
        List<MealPlanResponseDTO> records = userMealPlanService.findByUser(userId);
        return ResponseEntity.ok(records);
    }
}
