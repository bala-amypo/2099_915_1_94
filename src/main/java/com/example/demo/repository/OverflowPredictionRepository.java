package com.example.demo.repository;

import com.example.demo.model.OverflowPrediction;
import com.example.demo.model.Zone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OverflowPredictionRepository
        extends JpaRepository<OverflowPrediction, Long> {

    @Query("""
        SELECT p
        FROM OverflowPrediction p
        WHERE p.bin.zone = :zone
        ORDER BY p.predictedFullDate DESC
    """)
    List<OverflowPrediction> findLatestPredictionsForZone(
            @Param("zone") Zone zone
    );
}
