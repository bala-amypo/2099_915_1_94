package com.example.demo.service.impl;

import com.example.demo.model.*;
import com.example.demo.repository.*;
import com.example.demo.service.OverflowPredictionService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class OverflowPredictionServiceImpl implements OverflowPredictionService {

    private final OverflowPredictionRepository predictionRepository;
    private final UsagePatternModelRepository modelRepository;
    private final BinRepository binRepository;
    private final ZoneRepository zoneRepository;

    public OverflowPredictionServiceImpl(
            OverflowPredictionRepository predictionRepository,
            UsagePatternModelRepository modelRepository,
            BinRepository binRepository,
            ZoneRepository zoneRepository) {
        this.predictionRepository = predictionRepository;
        this.modelRepository = modelRepository;
        this.binRepository = binRepository;
        this.zoneRepository = zoneRepository;
    }

    @Override
    public OverflowPrediction predictOverflow(Long binId) {

        Bin bin = binRepository.findById(binId)
                .orElseThrow(() -> new RuntimeException("Bin not found"));

        UsagePatternModel model = modelRepository
                .findTop1ByBinOrderByLastUpdatedDesc(bin)
                .orElseThrow(() -> new RuntimeException("Model not found"));

        int daysUntilFull = 3;

        OverflowPrediction prediction = new OverflowPrediction();
        prediction.setBin(bin);
        prediction.setModelUsed(model);
        prediction.setDaysUntilFull(daysUntilFull);
        prediction.setPredictedFullDate(LocalDate.now().plusDays(daysUntilFull));

        return predictionRepository.save(prediction);
    }

    @Override
    public List<OverflowPrediction> getPredictionsForZone(Long zoneId) {
        Zone zone = zoneRepository.findById(zoneId)
                .orElseThrow(() -> new RuntimeException("Zone not found"));
        return predictionRepository.findLatestPredictionsForZone(zone);
    }
}
