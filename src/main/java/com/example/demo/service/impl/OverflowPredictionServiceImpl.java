package com.example.demo.service.impl;

import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.*;
import com.example.demo.repository.*;
import com.example.demo.service.OverflowPredictionService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service   // ⭐ THIS IS WHAT SPRING NEEDS
public class OverflowPredictionServiceImpl implements OverflowPredictionService {

    private final BinRepository binRepository;
    private final FillLevelRecordRepository recordRepository;
    private final UsagePatternModelRepository modelRepository;
    private final OverflowPredictionRepository predictionRepository;
    private final ZoneRepository zoneRepository;

    public OverflowPredictionServiceImpl(
            BinRepository binRepository,
            FillLevelRecordRepository recordRepository,
            UsagePatternModelRepository modelRepository,
            OverflowPredictionRepository predictionRepository,
            ZoneRepository zoneRepository
    ) {
        this.binRepository = binRepository;
        this.recordRepository = recordRepository;
        this.modelRepository = modelRepository;
        this.predictionRepository = predictionRepository;
        this.zoneRepository = zoneRepository;
    }

    @Override
    public OverflowPrediction generatePrediction(Long binId) {

        Bin bin = binRepository.findById(binId)
                .orElseThrow(() -> new ResourceNotFoundException("Bin not found"));

        if (!bin.getActive()) {
            throw new BadRequestException("Bin is inactive");
        }

        FillLevelRecord latestRecord =
                recordRepository.findTop1ByBinOrderByRecordedAtDesc(bin)
                        .orElseThrow(() -> new BadRequestException("No fill level data found"));

        UsagePatternModel model =
                modelRepository.findTop1ByBinOrderByLastUpdatedDesc(bin)
                        .orElseThrow(() -> new BadRequestException("Usage model not found"));

        double remaining = 100 - latestRecord.getFillPercentage();
        double dailyIncrease = model.getAvgDailyIncreaseWeekday();

        if (dailyIncrease <= 0) {
            throw new BadRequestException("Invalid usage pattern");
        }

        int daysUntilFull = (int) Math.ceil(remaining / dailyIncrease);

        OverflowPrediction prediction = new OverflowPrediction();
        prediction.setBin(bin);
        prediction.setDaysUntilFull(daysUntilFull);
        prediction.setPredictedFullDate(LocalDate.now().plusDays(daysUntilFull));
        prediction.setModelUsed(model);

        return predictionRepository.save(prediction);
    }

    @Override
    public List<OverflowPrediction> getLatestPredictionsForZone(Long zoneId) {

        Zone zone = zoneRepository.findById(zoneId)
                .orElseThrow(() -> new ResourceNotFoundException("Zone not found"));

        return predictionRepository.findLatestPredictionsForZone(zone);
    }
}
