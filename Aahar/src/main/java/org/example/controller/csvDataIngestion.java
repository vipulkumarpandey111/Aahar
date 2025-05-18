package org.example.controller;

import org.apache.commons.csv.*;
import org.example.entity.MealPlan;
import org.example.repository.MealPlanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStreamReader;
import java.io.Reader;
import java.util.*;

@RestController
@RequestMapping("/api/data")
public class csvDataIngestion {
    @Autowired
    private MealPlanRepository mealPlanRepository;

    @PostMapping("/upload-meals")
    public ResponseEntity<?> uploadCsv(@RequestParam("diet_plan_data") MultipartFile file) {
        try (Reader reader = new InputStreamReader(file.getInputStream())) {
            Iterable<CSVRecord> records = CSVFormat.DEFAULT
                    .withFirstRecordAsHeader()
                    .parse(reader);

            List<MealPlan> mealPlans = new ArrayList<>();
            for (CSVRecord record : records) {
                mealPlans.add(new MealPlan(
                        record.get("region"),
                        record.get("foodPreference"),
                        record.get("breakfast"),
                        record.get("lunch"),
                        record.get("dinner"),
                        Integer.parseInt(record.get("suggestedBudget"))
                ));
            }

            mealPlanRepository.saveAll(mealPlans);
            return ResponseEntity.ok("Uploaded successfully!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + e.getMessage());
        }
    }
}
