package com.example.demo.service.impl;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.*;
import com.example.demo.repository.*;
import com.example.demo.service.OverflowPredictionService;
import com.example.demo.util.WeekendUtil;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;

@Service
public class OverflowPredictionServiceImpl implements OverflowPredictionService {
    
    private final BinRepository binRepository;
    private final FillLevelRecordRepository recordRepository;
    private final UsagePatternModelRepository modelRepository;
    private final OverflowPredictionRepository predictionRepository;
    private final ZoneRepository zoneRepository;
    
    public OverflowPredictionServiceImpl(BinRepository binRepository, 
                                       FillLevelRecordRepository recordRepository,
                                       UsagePatternModelRepository modelRepository,
                                       OverflowPredictionRepository predictionRepository,
                                       ZoneRepository zoneRepository) {
        this.binRepository = binRepository;
        this.recordRepository = recordRepository;
        this.modelRepository = modelRepository;
        this.predictionRepository = predictionRepository;
        this.zoneRepository = zoneRepository;
    }
    
    @Override
    public OverflowPrediction generatePrediction(Long binId) {
        if (binId == null) {
            throw new ResourceNotFoundException("Bin ID cannot be null");
        }
        
        Bin bin = binRepository.findById(binId)
                .orElseThrow(() -> new ResourceNotFoundException("Bin not found"));
        
        FillLevelRecord latestRecord = recordRepository.findTop1ByBinOrderByRecordedAtDesc(bin)
                .orElseThrow(() -> new ResourceNotFoundException("No fill records found for bin"));
        
        UsagePatternModel model = modelRepository.findTop1ByBinOrderByLastUpdatedDesc(bin)
                .orElseThrow(() -> new ResourceNotFoundException("No usage model found for bin"));
        
        double currentFillPercentage = latestRecord.getFillPercentage();
        double remainingCapacity = 100.0 - currentFillPercentage;
        
        // Simple prediction logic
        double avgDailyIncrease = WeekendUtil.isWeekend(LocalDate.now()) ? 
                model.getAvgDailyIncreaseWeekend() : model.getAvgDailyIncreaseWeekday();
        
        int daysUntilFull = (int) Math.ceil(remainingCapacity / avgDailyIncrease);
        LocalDate predictedFullDate = LocalDate.now().plusDays(daysUntilFull);
        
        OverflowPrediction prediction = new OverflowPrediction(bin, model, daysUntilFull, predictedFullDate);
        return predictionRepository.save(prediction);
    }
    
    @Override
    public OverflowPrediction getPredictionById(Long id) {
        if (id == null) {
            throw new ResourceNotFoundException("ID cannot be null");
        }
        return predictionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Prediction not found with id: " + id));
    }
    
    @Override
    public List<OverflowPrediction> getLatestPredictionsForZone(Long zoneId) {
        if (zoneId == null) {
            throw new ResourceNotFoundException("Zone ID cannot be null");
        }
        
        Zone zone = zoneRepository.findById(zoneId)
                .orElseThrow(() -> new ResourceNotFoundException("Zone not found"));
        
        return predictionRepository.findLatestPredictionsForZone(zone);
    }
    
    @Override
    public List<OverflowPrediction> getAllPredictions() {
        return predictionRepository.findAll();
    }
}