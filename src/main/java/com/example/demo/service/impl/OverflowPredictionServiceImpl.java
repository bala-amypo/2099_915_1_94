package com.example.demo.service.impl;

import com.example.demo.model.OverflowPrediction;
import com.example.demo.model.Zone;
import com.example.demo.repository.BinRepository;
import com.example.demo.repository.OverflowPredictionRepository;
import com.example.demo.service.OverflowPredictionService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OverflowPredictionServiceImpl implements OverflowPredictionService {

    private final OverflowPredictionRepository predictionRepository;
    private final BinRepository binRepository;

    public OverflowPredictionServiceImpl(OverflowPredictionRepository predictionRepository,
                                        BinRepository binRepository) {
        this.predictionRepository = predictionRepository;
        this.binRepository = binRepository;
    }

    @Override
    public List<OverflowPrediction> getLatestPredictionsForZone(Long zoneId) {
        // Fetch the Zone entity first
        Zone zone = binRepository.findZoneById(zoneId);
        // Use the correct repository method
        return predictionRepository.findByBin_ZoneOrderByPredictedFullDateAsc(zone);
    }
}
