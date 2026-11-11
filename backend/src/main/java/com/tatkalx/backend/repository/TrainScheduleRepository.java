package com.tatkalx.backend.repository;

import com.tatkalx.backend.entity.Train;
import com.tatkalx.backend.entity.TrainSchedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TrainScheduleRepository extends JpaRepository<TrainSchedule, Long> {

    // JOIN FETCH loads the Station in the exact same SQL query so Hibernate doesn't throw LazyInitializationException
    @Query("""
        SELECT ts FROM TrainSchedule ts
        JOIN FETCH ts.station
        WHERE ts.train.id = :trainId
        ORDER BY ts.stopOrder ASC
    """)
    List<TrainSchedule> findByTrainIdOrderByStopOrderAsc(@Param("trainId") Long trainId);

    // Core Route Search Query
    @Query("""
        SELECT DISTINCT ts1.train
        FROM TrainSchedule ts1
        JOIN TrainSchedule ts2 ON ts1.train.id = ts2.train.id
        WHERE UPPER(ts1.station.stationCode) = UPPER(:sourceCode)
          AND UPPER(ts2.station.stationCode) = UPPER(:destCode)
          AND ts1.stopOrder < ts2.stopOrder
    """)
    List<Train> findTrainsBetweenStations(
            @Param("sourceCode") String sourceCode,
            @Param("destCode") String destCode
    );
}