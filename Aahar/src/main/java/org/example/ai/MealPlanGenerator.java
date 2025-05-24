package org.example.ai;

import org.example.dto.*;

public interface MealPlanGenerator {
    MealPlanResponseDTO generate(MealPlanRequestDTO request);
}
