package com.tatkalx.backend.dto;

import com.tatkalx.backend.entity.TrainSchedule;
import java.time.LocalTime;

public record TrainScheduleResponse(
        Long id,
        Integer stopOrder,
        String stationCode,
        String stationName,
        LocalTime arrivalTime,
        LocalTime departureTime,
        Integer haltDurationMinutes,
        Integer distanceFromSourceKm
) {
    public static TrainScheduleResponse fromEntity(TrainSchedule schedule) {
        return new TrainScheduleResponse(
                schedule.getId(),
                schedule.getStopOrder(),
                schedule.getStation().getStationCode(),
                schedule.getStation().getStationName(),
                schedule.getArrivalTime(),
                schedule.getDepartureTime(),
                schedule.getHaltDurationMinutes(),
                schedule.getDistanceFromSourceKm()
        );
    }
}