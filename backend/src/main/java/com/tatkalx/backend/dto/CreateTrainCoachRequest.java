package com.tatkalx.backend.dto;

import com.tatkalx.backend.entity.CoachType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public record CreateTrainCoachRequest(
        @NotBlank(message = "Coach number is required")
        @Size(max = 10, message = "Coach number cannot exceed 10 characters")
        String coachNumber,

        @NotNull(message = "Coach type is required")
        CoachType coachType,

        @NotNull(message = "Total seats is required")
        @Positive(message = "Total seats must be greater than 0")
        Integer totalSeats
) {
}