package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.model.OverflowPrediction;
import com.example.demo.model.Zone;

public interface OverflowPredictionRepository
        extends JpaRepository<OverflowPrediction, Long> {

    @Query("""
        SELECT op
        FROM OverflowPrediction op
        WHERE op.bin.zone = :zone
        ORDER BY op.predictedFullDate DESC
    """)
    List<OverflowPrediction> findLatestPredictionsForZone(@Param("zone") Zone zone);
}
