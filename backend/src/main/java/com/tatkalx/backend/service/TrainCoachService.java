package com.tatkalx.backend.service;

import com.tatkalx.backend.dto.CreateTrainCoachRequest;
import com.tatkalx.backend.entity.CoachType;
import com.tatkalx.backend.entity.Train;
import com.tatkalx.backend.entity.TrainCoach;
import com.tatkalx.backend.exception.ResourceNotFoundException;
import com.tatkalx.backend.repository.TrainCoachRepository;
import com.tatkalx.backend.repository.TrainRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TrainCoachService {

    private final TrainCoachRepository trainCoachRepository;
    private final TrainRepository trainRepository;

    public TrainCoachService(TrainCoachRepository trainCoachRepository, TrainRepository trainRepository) {
        this.trainCoachRepository = trainCoachRepository;
        this.trainRepository = trainRepository;
    }

    public TrainCoach addCoachToTrain(Long trainId, CreateTrainCoachRequest request) {
        Train train = trainRepository.findById(trainId)
                .orElseThrow(() -> new ResourceNotFoundException("Train not found with id: " + trainId));

        TrainCoach coach = new TrainCoach(
                train,
                request.coachNumber().trim().toUpperCase(),
                request.coachType(),
                request.totalSeats()
        );

        return trainCoachRepository.save(coach);
    }

    public List<TrainCoach> getCoachesByTrainId(Long trainId) {
        if (!trainRepository.existsById(trainId)) {
            throw new ResourceNotFoundException("Train not found with id: " + trainId);
        }
        return trainCoachRepository.findByTrainIdOrderByCoachNumberAsc(trainId);
    }

    public Integer getTotalSeatsByClass(Long trainId, String coachTypeCode) {
        if (!trainRepository.existsById(trainId)) {
            throw new ResourceNotFoundException("Train not found with id: " + trainId);
        }
        CoachType coachType = CoachType.fromCode(coachTypeCode.trim());
        return trainCoachRepository.countTotalSeatsByTrainAndClass(trainId, coachType);
    }
}