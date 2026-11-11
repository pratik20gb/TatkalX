package com.tatkalx.backend.controller;

import com.tatkalx.backend.dto.CreateStationRequest;
import com.tatkalx.backend.dto.StationResponse;
import com.tatkalx.backend.entity.Station;
import com.tatkalx.backend.repository.StationRepository;
import com.tatkalx.backend.service.StationService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/stations")
public class StationController {
    private StationService stationService;
    public StationController(StationService stationService) {
        this.stationService = stationService;
    }
    @PostMapping
    public ResponseEntity<StationResponse> createStation(@Valid @RequestBody CreateStationRequest request) {
        Station station = new Station(
                request.stationCode().toUpperCase().trim(),
                request.stationName().trim(),
                request.city().trim(),
                request.state().trim()
        );
        Station saved = stationService.createStation(station);
        return ResponseEntity.status(HttpStatus.CREATED).body(StationResponse.fromEntity(saved));
    }

    @GetMapping
    public ResponseEntity<List<StationResponse>> getAllStations() {
        List<StationResponse> response= stationService.getAllStations()
                .stream()
                .map(StationResponse::fromEntity)
                .toList();
        return  ResponseEntity.ok(response);
    }
    @GetMapping("/{code}")
    public ResponseEntity<StationResponse> getStationByCode(@PathVariable String code) {
        Station station = stationService.getStationByCode(code);
        return ResponseEntity.ok(StationResponse.fromEntity(station));
    }
}
