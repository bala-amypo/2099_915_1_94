package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Bin;
import com.example.demo.model.UsagePatternModel;

public interface UsagePatternModelRepository
        extends JpaRepository<UsagePatternModel, Long> {

    Optional<UsagePatternModel> findByBin(Bin bin);

    // ✅ REQUIRED for OverflowPredictionService
    Optional<UsagePatternModel> findTopByBinOrderByLastUpdatedDesc(Bin bin);
}
