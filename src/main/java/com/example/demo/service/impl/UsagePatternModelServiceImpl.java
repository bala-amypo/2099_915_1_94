package com.example.demo.service.impl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.model.Bin;
import com.example.demo.model.UsagePatternModel;
import com.example.demo.repository.BinRepository;
import com.example.demo.repository.UsagePatternModelRepository;
import com.example.demo.service.UsagePatternModelService;

@Service
public class UsagePatternModelServiceImpl implements UsagePatternModelService {

    private final UsagePatternModelRepository modelRepository;
    private final BinRepository binRepository;

    // ✅ Constructor injection (recommended)
    public UsagePatternModelServiceImpl(
            UsagePatternModelRepository modelRepository,
            BinRepository binRepository) {
        this.modelRepository = modelRepository;
        this.binRepository = binRepository;
    }

    // ✅ Create new usage pattern
    @Override
    public UsagePatternModel createModel(UsagePatternModel model) {

        model.setLastUpdated(LocalDateTime.now());
        return modelRepository.save(model);
    }

    // ✅ Get all models
    @Override
    public List<UsagePatternModel> getAllModels() {
        return modelRepository.findAll();
    }

    // ✅ Get model by ID
    @Override
    public UsagePatternModel getModelById(Long id) {
        return modelRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("UsagePatternModel not found with id: " + id));
    }

    // ✅ Get model by Bin ID (THIS FIXES YOUR ERROR)
    @Override
    public UsagePatternModel getModelByBinId(Long binId) {

        Bin bin = binRepository.findById(binId)
                .orElseThrow(() ->
                        new RuntimeException("Bin not found with id: " + binId));

        return modelRepository.findByBin(bin)
                .orElseThrow(() ->
                        new RuntimeException("UsagePatternModel not found for bin id: " + binId));
    }

    // ✅ Update model
    @Override
    public UsagePatternModel updateModel(Long id, UsagePatternModel updatedModel) {

        UsagePatternModel existing = getModelById(id);

        existing.setAvgDailyIncreaseWeekday(
                updatedModel.getAvgDailyIncreaseWeekday());
        existing.setAvgDailyIncreaseWeekend(
                updatedModel.getAvgDailyIncreaseWeekend());

        existing.setLastUpdated(LocalDateTime.now());

        return modelRepository.save(existing);
    }
}
