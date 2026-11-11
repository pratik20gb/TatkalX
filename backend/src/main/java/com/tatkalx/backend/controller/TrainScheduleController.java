package com.tatkalx.backend.controller;

import com.tatkalx.backend.dto.TrainResponse;
import com.tatkalx.backend.dto.TrainScheduleResponse;
import com.tatkalx.backend.entity.Train;
import com.tatkalx.backend.entity.TrainSchedule;
import com.tatkalx.backend.service.TrainScheduleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/schedules")
public class TrainScheduleController {

    private final TrainScheduleService scheduleService;

    public TrainScheduleController(TrainScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }

    // 1. Get full timetable for a train (ordered Stop 1, 2, 3...)
    @GetMapping("/train/{trainId}")
    public ResponseEntity<List<TrainScheduleResponse>> getTrainSchedule(@PathVariable Long trainId) {
        List<TrainSchedule> schedules = scheduleService.getScheduleByTrainId(trainId);
        List<TrainScheduleResponse> response = schedules.stream()
                .map(TrainScheduleResponse::fromEntity)
                .toList();

        return ResponseEntity.ok(response);
    }

    // 2. Search trains running between two stations (checks intermediate stops in sequence)
    @GetMapping("/search")
    public ResponseEntity<List<TrainResponse>> searchTrains(
            @RequestParam String from,
            @RequestParam String to
    ) {
        List<Train> trains = scheduleService.searchTrainsBetweenStations(from, to);
        List<TrainResponse> response = trains.stream()
                .map(TrainResponse::fromEntity)
                .toList();

        return ResponseEntity.ok(response);
    }
}