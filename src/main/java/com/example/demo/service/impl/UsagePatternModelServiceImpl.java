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
public class UsagePatternModelServiceImpl
        implements UsagePatternModelService {

    private final UsagePatternModelRepository modelRepository;
    private final BinRepository binRepository;

    public UsagePatternModelServiceImpl(
            UsagePatternModelRepository modelRepository,
            BinRepository binRepository) {
        this.modelRepository = modelRepository;
        this.binRepository = binRepository;
    }

    @Override
    public UsagePatternModel create(UsagePatternModel model) {
        model.setLastUpdated(LocalDateTime.now());
        return modelRepository.save(model);
    }

    @Override
    public UsagePatternModel update(Long id, UsagePatternModel model) {

        UsagePatternModel existing = getById(id);

        existing.setAvgDailyIncreaseWeekday(
                model.getAvgDailyIncreaseWeekday());
        existing.setAvgDailyIncreaseWeekend(
                model.getAvgDailyIncreaseWeekend());

        existing.setLastUpdated(LocalDateTime.now());
        return modelRepository.save(existing);
    }

    @Override
    public UsagePatternModel getById(Long id) {
        return modelRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Model not found: " + id));
    }

    @Override
    public UsagePatternModel getByBinId(Long binId) {

        Bin bin = binRepository.findById(binId)
                .orElseThrow(() ->
                        new RuntimeException("Bin not found: " + binId));

        return modelRepository.findByBin(bin)
                .orElseThrow(() ->
                        new RuntimeException("Model not found for bin"));
    }

    @Override
    public List<UsagePatternModel> getAll() {
        return modelRepository.findAll();
    }
}
