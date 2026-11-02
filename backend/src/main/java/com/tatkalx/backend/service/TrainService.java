package com.tatkalx.backend.service;

import com.tatkalx.backend.entity.Train;
import com.tatkalx.backend.repository.TrainRepository;
import org.springframework.stereotype.Service;

@Service
public class TrainService {
    private final TrainRepository trainRepository;
    public TrainService(TrainRepository trainRepository) {
        this.trainRepository = trainRepository;
    }
    public Train createTrain(Train train) {
        return trainRepository.save(train);
    }



}
