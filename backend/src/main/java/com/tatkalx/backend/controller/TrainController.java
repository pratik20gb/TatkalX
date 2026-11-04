package com.tatkalx.backend.controller;

import com.tatkalx.backend.dto.CreateTrainRequest;
import com.tatkalx.backend.dto.PagedResponse;
import com.tatkalx.backend.dto.TrainResponse;
import com.tatkalx.backend.entity.Train;
import com.tatkalx.backend.service.TrainService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
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
    // UPDATED: Search with pagination
    @GetMapping("/search")
    public ResponseEntity<PagedResponse<TrainResponse>> searchTrains(
            @RequestParam String source,
            @RequestParam String destination,
            @PageableDefault(page = 0, size = 10) Pageable pageable) {

        Page<Train> trainPage = trainService.searchTrains(source, destination, pageable);

        // 1. Transform the page's Train entities into TrainResponse DTOs
        List<TrainResponse> content = trainPage.getContent()
                .stream()
                .map(TrainResponse::fromEntity)
                .toList();

        // 2. Package everything into our generic PagedResponse envelope
        PagedResponse<TrainResponse> response = new PagedResponse<>(
                content,
                trainPage.getNumber(),
                trainPage.getSize(),
                trainPage.getTotalElements(),
                trainPage.getTotalPages(),
                trainPage.isLast()
        );

        return ResponseEntity.ok(response);
    }
}
