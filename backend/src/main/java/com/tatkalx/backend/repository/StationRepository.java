package com.tatkalx.backend.repository;

import com.tatkalx.backend.entity.Station;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StationRepository extends JpaRepository<Station, Long> {

    Optional<Station> findByStationCodeIgnoreCase(String stationCode);

    boolean existsByStationCodeIgnoreCase(String stationCode);
}