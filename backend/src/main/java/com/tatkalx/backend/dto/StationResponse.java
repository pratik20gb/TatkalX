package com.tatkalx.backend.dto;

import com.tatkalx.backend.entity.Station;

public record StationResponse(
        Long id,
        String stationCode,
        String stationName,
        String city,
        String state
) {
    public static StationResponse fromEntity(Station station) {
        return new StationResponse(
                station.getId(),
                station.getStationCode(),
                station.getStationName(),
                station.getCity(),
                station.getState()
        );
    }
}