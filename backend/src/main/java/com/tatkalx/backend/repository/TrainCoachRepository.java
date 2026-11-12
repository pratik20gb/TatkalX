package com.tatkalx.backend.repository;

import com.tatkalx.backend.entity.CoachType;
import com.tatkalx.backend.entity.TrainCoach;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TrainCoachRepository extends JpaRepository<TrainCoach, Long> {

    List<TrainCoach> findByTrainIdOrderByCoachNumberAsc(Long trainId);

    // Calculate total capacity for a specific class on a train (e.g., total 3A seats on Train 12002)
    @Query("""
        SELECT COALESCE(SUM(tc.totalSeats), 0)
        FROM TrainCoach tc
        WHERE tc.train.id = :trainId AND tc.coachType = :coachType
    """)
    Integer countTotalSeatsByTrainAndClass(
            @Param("trainId") Long trainId,
            @Param("coachType") CoachType coachType
    );
}