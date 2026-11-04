package com.tatkalx.backend.repository;

import com.tatkalx.backend.entity.Train;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrainRepository extends JpaRepository<Train, Long> {

    Page<Train> findBySourceStationIgnoreCaseAndDestinationStationIgnoreCase(
            String sourceStation,
            String destinationStation,
            Pageable pageable
    );
}