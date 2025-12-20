package com.example.demo.service.impl;

import com.example.demo.model.Bin;
import com.example.demo.model.FillLevelRecord;
import com.example.demo.model.UsagePatternModel;
import com.example.demo.repository.BinRepository;
import com.example.demo.repository.FillLevelRecordRepository;
import com.example.demo.repository.UsagePatternModelRepository;
import com.example.demo.service.UsagePatternModelService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class UsagePatternModelServiceImpl implements UsagePatternModelService {

    private final UsagePatternModelRepository modelRepository;
    private final FillLevelRecordRepository recordRepository;
    private final BinRepository binRepository;

    public UsagePatternModelServiceImpl(
            UsagePatternModelRepository modelRepository,
            FillLevelRecordRepository recordRepository,
            BinRepository binRepository) {
        this.modelRepository = modelRepository;
        this.recordRepository = recordRepository;
        this.binRepository = binRepository;
    }

    @Override
    public UsagePatternModel generateOrUpdateModel(Long binId) {

        Bin bin = binRepository.findById(binId)
                .orElseThrow(() -> new RuntimeException("Bin not found"));

        List<FillLevelRecord> records =
                recordRepository.findByBinOrderByRecordedAtDesc(bin);

        double weekdayAvg = records.isEmpty() ? 0 : 10.0;
        double weekendAvg = records.isEmpty() ? 0 : 5.0;

        UsagePatternModel model = new UsagePatternModel();
        model.setBin(bin);
        model.setAvgDailyIncreaseWeekday(weekdayAvg);
        model.setAvgDailyIncreaseWeekend(weekendAvg);
        model.setLastUpdated(LocalDateTime.now());

        return modelRepository.save(model);
    }
}
