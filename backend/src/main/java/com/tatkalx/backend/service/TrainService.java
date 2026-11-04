package com.tatkalx.backend.service;

import com.tatkalx.backend.entity.Train;
import com.tatkalx.backend.exception.ResourceNotFoundException;
import com.tatkalx.backend.repository.TrainRepository;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

@Service
public class TrainService {
    private final TrainRepository trainRepository;
    public TrainService(TrainRepository trainRepository) {
        this.trainRepository = trainRepository;
    }
    public Train createTrain(Train train) {
        return trainRepository.save(train);
    }
    public List<Train> getAllTrains() {
        return trainRepository.findAll();}

    public Train getTrainById(Long id) {
        return trainRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Train not found with id " + id ));
    }
    // UPDATED: Now accepts Pageable and returns Page<Train>
    public Page<Train> searchTrains(String source, String destination, Pageable pageable) {
        return trainRepository.findBySourceStationIgnoreCaseAndDestinationStationIgnoreCase(
                source,
                destination,
                pageable
        );
    }
}
