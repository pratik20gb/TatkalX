package com.tatkalx.backend.dto;

import com.tatkalx.backend.entity.CoachType;
import com.tatkalx.backend.entity.TrainCoach;

public record TrainCoachResponse(
        Long id,
        String coachNumber,
        CoachType coachType,
        Integer totalSeats
) {
    public static TrainCoachResponse fromEntity(TrainCoach coach) {
        return new TrainCoachResponse(
                coach.getId(),
                coach.getCoachNumber(),
                coach.getCoachType(),
                coach.getTotalSeats()
        );
    }
}