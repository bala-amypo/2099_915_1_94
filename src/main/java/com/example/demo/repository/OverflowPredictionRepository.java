package com.example.demo.repository;

import com.example.demo.model.OverflowPrediction;
import com.example.demo.model.Zone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OverflowPredictionRepository extends JpaRepository<OverflowPrediction, Long> {

    // JPQL query to get latest predictions for a zone
    @Query("SELECT o FROM OverflowPrediction o WHERE o.zone = :zone ORDER BY o.timestamp DESC")
    List<OverflowPrediction> findLatestPredictionsForZone(@Param("zone") Zone zone);
}
