package com.example.demo.service.impl;

import com.example.demo.model.OverflowPrediction;
import com.example.demo.model.Zone;
import com.example.demo.repository.BinRepository;
import com.example.demo.repository.OverflowPredictionRepository;
import com.example.demo.service.OverflowPredictionService;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
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
        // Fetch the Zone entity from BinRepository
        Zone zone = binRepository.findById(zoneId)
                .map(bin -> bin.getZone())
                .orElseThrow(() -> new RuntimeException("Zone not found for ID: " + zoneId));

        return predictionRepository.findByBin_ZoneOrderByPredictedFullDateAsc(zone);
    }

    @Override
    public OverflowPrediction generatePrediction(Long binId) {
        // Create a new OverflowPrediction
        OverflowPrediction prediction = new OverflowPrediction();
        prediction.setBin(binRepository.findById(binId)
                .orElseThrow(() -> new RuntimeException("Bin not found: " + binId)));

        // Set predicted date as today's date
        LocalDate today = Instant.ofEpochMilli(System.currentTimeMillis())
                                 .atZone(ZoneId.systemDefault())
                                 .toLocalDate();
        prediction.setPredictedFullDate(today);

        // Save to DB
        return predictionRepository.save(prediction);
    }
}
