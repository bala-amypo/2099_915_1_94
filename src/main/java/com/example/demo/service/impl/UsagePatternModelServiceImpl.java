package com.example.demo.service.impl;

import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Bin;
import com.example.demo.model.UsagePatternModel;
import com.example.demo.repository.BinRepository;
import com.example.demo.repository.UsagePatternModelRepository;
import com.example.demo.service.UsagePatternModelService;

import java.time.LocalDateTime;

public class UsagePatternModelServiceImpl implements UsagePatternModelService {

    private final UsagePatternModelRepository modelRepository;
    private final BinRepository binRepository;

    public UsagePatternModelServiceImpl(UsagePatternModelRepository modelRepository,
                                        BinRepository binRepository) {
        this.modelRepository = modelRepository;
        this.binRepository = binRepository;
    }

    @Override
    public UsagePatternModel createModel(UsagePatternModel model) {
        Bin bin = binRepository.findById(model.getBin().getId())
                .orElseThrow(() -> new BadRequestException("Bin not found"));

        if (model.getAvgDailyIncreaseWeekday() < 0 ||
            model.getAvgDailyIncreaseWeekend() < 0) {
            throw new BadRequestException("Negative increase");
        }

        model.setBin(bin);
        model.setLastUpdated(LocalDateTime.now());
        return modelRepository.save(model);
    }

    @Override
    public UsagePatternModel updateModel(Long id, UsagePatternModel model) {
        UsagePatternModel existing = modelRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Model not found"));

        if (model.getAvgDailyIncreaseWeekday() != null) {
            existing.setAvgDailyIncreaseWeekday(model.getAvgDailyIncreaseWeekday());
        }
        if (model.getAvgDailyIncreaseWeekend() != null) {
            existing.setAvgDailyIncreaseWeekend(model.getAvgDailyIncreaseWeekend());
        }

        existing.setLastUpdated(LocalDateTime.now());
        return existing;
    }

    @Override
    public UsagePatternModel getModelForBin(Long binId) {
        Bin bin = binRepository.findById(binId)
                .orElseThrow(() -> new ResourceNotFoundException("Bin not found"));

        return modelRepository.findTop1ByBinOrderByLastUpdatedDesc(bin)
                .orElseThrow(() -> new ResourceNotFoundException("Model not found"));
    }
}
