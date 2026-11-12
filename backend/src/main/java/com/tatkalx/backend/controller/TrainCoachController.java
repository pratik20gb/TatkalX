package com.tatkalx.backend.controller;

import com.tatkalx.backend.dto.CreateTrainCoachRequest;
import com.tatkalx.backend.dto.TrainCoachResponse;
import com.tatkalx.backend.entity.TrainCoach;
import com.tatkalx.backend.service.TrainCoachService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/trains/{trainId}/coaches")
public class TrainCoachController {

    private final TrainCoachService trainCoachService;

    public TrainCoachController(TrainCoachService trainCoachService) {
        this.trainCoachService = trainCoachService;
    }

    // 1. Add a new coach to a train
    @PostMapping
    public ResponseEntity<TrainCoachResponse> addCoach(
            @PathVariable Long trainId,
            @Valid @RequestBody CreateTrainCoachRequest request
    ) {
        TrainCoach saved = trainCoachService.addCoachToTrain(trainId, request);
        return ResponseEntity.status(HttpStatus.CREATED).body(TrainCoachResponse.fromEntity(saved));
    }

    // 2. Get all coaches attached to a train
    @GetMapping
    public ResponseEntity<List<TrainCoachResponse>> getTrainCoaches(@PathVariable Long trainId) {
        List<TrainCoachResponse> response = trainCoachService.getCoachesByTrainId(trainId)
                .stream()
                .map(TrainCoachResponse::fromEntity)
                .toList();

        return ResponseEntity.ok(response);
    }

    // 3. Get total seat capacity for a specific travel class (e.g., ?coachType=3A)
    @GetMapping("/capacity")
    public ResponseEntity<Map<String,Object>> getClassCapacity(
            @PathVariable Long trainId,
            @RequestParam String coachType
    ) {
        Integer totalSeats = trainCoachService.getTotalSeatsByClass(trainId, coachType);
        return ResponseEntity.ok(Map.of(
                "trainId", trainId,
                "coachType", coachType.toUpperCase(),
                "totalSeats", totalSeats
        ));
    }
}