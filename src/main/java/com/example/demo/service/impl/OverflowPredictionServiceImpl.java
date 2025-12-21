package com.example.demo.service.impl;

import com.example.demo.model.*;
import com.example.demo.repository.*;
import com.example.demo.service.OverflowPredictionService;

import java.time.LocalDate;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
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
            ZoneRepository zoneRepository) {

        this.binRepository = binRepository;
        this.recordRepository = recordRepository;
        this.modelRepository = modelRepository;
        this.predictionRepository = predictionRepository;
        this.zoneRepository = zoneRepository;
    }

    public OverflowPrediction generatePrediction(Long binId) {
        Bin bin = binRepository.findById(binId).get();
        FillLevelRecord record =
                recordRepository.findTop1ByBinOrderByRecordedAtDesc(bin).get();
        UsagePatternModel model =
                modelRepository.findTop1ByBinOrderByLastUpdatedDesc(bin).get();

        OverflowPrediction p = new OverflowPrediction();
        p.setBin(bin);
        p.setModelUsed(model);
        p.setDaysUntilFull(3);
        p.setPredictedFullDate(LocalDate.now().plusDays(3));

        return predictionRepository.save(p);
    }

    public List<OverflowPrediction> getLatestPredictionsForZone(Long zoneId) {
        Zone zone = zoneRepository.findById(zoneId).get();
        return predictionRepository.findLatestPredictionsForZone(zone);
    }
}
