package com.tatkalx.backend.controller;

import com.tatkalx.backend.dto.CreateTrainRequest;
import com.tatkalx.backend.dto.TrainResponse;
import com.tatkalx.backend.entity.Train;
import com.tatkalx.backend.service.TrainService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/trains")
public class TrainController {
    private final TrainService trainService;

    public TrainController(TrainService trainService) {
        this.trainService = trainService;
    }

    @PostMapping
    public ResponseEntity<TrainResponse> createTrain(@Valid @RequestBody CreateTrainRequest request) {
        Train train = new Train(
                request.trainNumber(),
                request.trainName(),
                request.sourceStation(),
                request.destinationStation()
        );

        Train savedTrain = trainService.createTrain(train);
        TrainResponse response = TrainResponse.fromEntity(savedTrain);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    @GetMapping
    public ResponseEntity<List<TrainResponse>> getAllTrains() {
        List<TrainResponse> response = trainService.getAllTrains()
                .stream()
                .map(TrainResponse::fromEntity)
                .toList();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TrainResponse> getTrainById(@PathVariable Long id) {
        Train train = trainService.getTrainById(id);
        return ResponseEntity.ok(TrainResponse.fromEntity(train));
    }
}
