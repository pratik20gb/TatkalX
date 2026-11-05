package com.tatkalx.backend.service;

import com.tatkalx.backend.entity.Station;
import com.tatkalx.backend.exception.ResourceNotFoundException;
import com.tatkalx.backend.repository.StationRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StationService {

    private final StationRepository stationRepository;

    public StationService(StationRepository stationRepository) {
        this.stationRepository = stationRepository;
    }

    public Station createStation(Station station) {
        return stationRepository.save(station);
    }

    public List<Station> getAllStations() {
        return stationRepository.findAll();
    }

    public Station getStationByCode(String stationCode) {
        return stationRepository.findByStationCodeIgnoreCase(stationCode)
                .orElseThrow(() -> new ResourceNotFoundException("Station not found with code: " + stationCode.toUpperCase()));
    }
}