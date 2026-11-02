package com.tatkalx.backend.repository;

import com.tatkalx.backend.entity.Train;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TrainRepository extends JpaRepository<Train,Long> {
}
