package com.example.demo.service.impl;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Bin;
import com.example.demo.model.OverflowPrediction;
import com.example.demo.model.UsagePatternModel;
import com.example.demo.model.Zone;
import com.example.demo.repository.*;
import com.example.demo.service.OverflowPredictionService;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;

@Service
public class OverflowPredictionServiceImpl implements OverflowPredictionService {

    private final BinRepository binRepository;
    private final OverflowPredictionRepository predictionRepository;
    private final UsagePatternModelRepository modelRepository;
    private final ZoneRepository zoneRepository;

    public OverflowPredictionServiceImpl(BinRepository binRepository,
                                         OverflowPredictionRepository predictionRepository,
                                         UsagePatternModelRepository modelRepository,
                                         ZoneRepository zoneRepository) {
        this.binRepository = binRepository;
        this.predictionRepository = predictionRepository;
        this.modelRepository = modelRepository;
        this.zoneRepository = zoneRepository;
    }

    @Override
    public OverflowPrediction generatePrediction(Long binId) {
        Bin bin = binRepository.findById(binId)
                .orElseThrow(() -> new ResourceNotFoundException("Bin not found"));

        UsagePatternModel model = modelRepository.findTop1ByBinOrderByLastUpdatedDesc(bin)
                .orElseThrow(() -> new ResourceNotFoundException("Model not found"));

        OverflowPrediction prediction = new OverflowPrediction();
        prediction.setBin(bin);
        prediction.setPredictedFullDate(LocalDate.now().plusDays(1));
        prediction.setDaysUntilFull(1);
        prediction.setModelUsed(model);

        return predictionRepository.save(prediction);
    }

    @Override
    public OverflowPrediction getPredictionById(Long id) {
        return predictionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Prediction not found"));
    }

    @Override
    public List<OverflowPrediction> getPredictionsForBin(Long binId) {
        Bin bin = binRepository.findById(binId)
                .orElseThrow(() -> new ResourceNotFoundException("Bin not found"));
        return predictionRepository.findAll().stream().filter(p -> p.getBin().equals(bin)).toList();
    }

    @Override
    public List<OverflowPrediction> getLatestPredictionsForZone(Long zoneId) {
        Zone zone = zoneRepository.findById(zoneId)
                .orElseThrow(() -> new ResourceNotFoundException("Zone not found"));
        return predictionRepository.findLatestPredictionsForZone(zone);
    }
}
