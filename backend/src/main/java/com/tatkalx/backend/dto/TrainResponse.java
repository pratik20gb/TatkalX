package com.tatkalx.backend.dto;

import com.tatkalx.backend.entity.Train;

public record TrainResponse(
        Long id,
        String trainNumber,
        String trainName,
        String sourceStation,
        String destinationStation
) {
    public static TrainResponse fromEntity(Train train){
        return new TrainResponse(
                train.getId(),
                train.getTrainNumber(),
                train.getTrainName(),
                train.getSourceStation(),
                train.getDestinationStation()
        );
    }
}
