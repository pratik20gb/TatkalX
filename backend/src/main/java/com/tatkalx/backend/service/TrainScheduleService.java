package com.tatkalx.backend.service;

import com.tatkalx.backend.entity.Train;
import com.tatkalx.backend.entity.TrainSchedule;
import com.tatkalx.backend.repository.TrainScheduleRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TrainScheduleService {

    private final TrainScheduleRepository trainScheduleRepository;

    public TrainScheduleService(TrainScheduleRepository trainScheduleRepository) {
        this.trainScheduleRepository = trainScheduleRepository;
    }

    public List<TrainSchedule> getScheduleByTrainId(Long trainId) {
        return trainScheduleRepository.findByTrainIdOrderByStopOrderAsc(trainId);
    }

    public List<Train> searchTrainsBetweenStations(String fromStationCode, String toStationCode) {
        return trainScheduleRepository.findTrainsBetweenStations(
                fromStationCode.trim().toUpperCase(),
                toStationCode.trim().toUpperCase()
        );
    }
}