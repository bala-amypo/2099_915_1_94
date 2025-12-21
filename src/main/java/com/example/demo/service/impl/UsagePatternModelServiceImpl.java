package com.example.demo.service.impl;

import com.example.demo.exception.*;
import com.example.demo.model.*;
import com.example.demo.repository.*;
import com.example.demo.service.UsagePatternModelService;

import java.util.List;

public class UsagePatternModelServiceImpl implements UsagePatternModelService {

    private final UsagePatternModelRepository modelRepository;
    private final BinRepository binRepository;

    public UsagePatternModelServiceImpl(
            UsagePatternModelRepository modelRepository,
            BinRepository binRepository) {
        this.modelRepository = modelRepository;
        this.binRepository = binRepository;
    }

    public UsagePatternModel createModel(UsagePatternModel model) {
        if (model.getAvgDailyIncreaseWeekday() < 0 ||
            model.getAvgDailyIncreaseWeekend() < 0) {
            throw new BadRequestException("negative");
        }

        binRepository.findById(model.getBin().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Bin not found"));

        return modelRepository.save(model);
    }

    public UsagePatternModel updateModel(Long id, UsagePatternModel model) {
        UsagePatternModel existing =
                modelRepository.findById(id)
                        .orElseThrow(() -> new ResourceNotFoundException("Model not found"));

        existing.setAvgDailyIncreaseWeekend(model.getAvgDailyIncreaseWeekend());
        return modelRepository.save(existing);
    }

    public UsagePatternModel getModelForBin(Long binId) {
        Bin bin = binRepository.findById(binId)
                .orElseThrow(() -> new ResourceNotFoundException("Bin not found"));

        return modelRepository.findTop1ByBinOrderByLastUpdatedDesc(bin)
                .orElseThrow(() -> new ResourceNotFoundException("Model not found"));
    }

    public List<UsagePatternModel> getAllModels() {
        return null;
    }
}
