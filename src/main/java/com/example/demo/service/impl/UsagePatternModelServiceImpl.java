package com.example.demo.service.impl;

import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Bin;
import com.example.demo.model.UsagePatternModel;
import com.example.demo.repository.BinRepository;
import com.example.demo.repository.UsagePatternModelRepository;
import com.example.demo.service.UsagePatternModelService;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UsagePatternModelServiceImpl implements UsagePatternModelService {
    
    private final UsagePatternModelRepository modelRepository;
    private final BinRepository binRepository;
    
    public UsagePatternModelServiceImpl(UsagePatternModelRepository modelRepository, BinRepository binRepository) {
        this.modelRepository = modelRepository;
        this.binRepository = binRepository;
    }
    
    @Override
    public UsagePatternModel createModel(UsagePatternModel model) {
        if (model.getBin() == null || model.getBin().getId() == null) {
            throw new BadRequestException("Bin is required");
        }
        
        @SuppressWarnings("null")
        Bin bin = binRepository.findById(model.getBin().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Bin not found"));
        
        if (!bin.getActive()) {
            throw new BadRequestException("Cannot create model for inactive bin");
        }
        
        if (model.getAvgDailyIncreaseWeekday() < 0 || model.getAvgDailyIncreaseWeekend() < 0) {
            throw new BadRequestException("Daily increase values cannot be negative");
        }
        
        return modelRepository.save(model);
    }
    
    @Override
    public UsagePatternModel getModelById(Long id) {
        if (id == null) {
            throw new BadRequestException("ID cannot be null");
        }
        return modelRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Model not found with id: " + id));
    }
    
    @Override
    public UsagePatternModel updateModel(Long id, UsagePatternModel model) {
        if (id == null) {
            throw new BadRequestException("ID cannot be null");
        }
        
        UsagePatternModel existing = getModelById(id);
        if (model.getAvgDailyIncreaseWeekday() != null) {
            existing.setAvgDailyIncreaseWeekday(model.getAvgDailyIncreaseWeekday());
        }
        if (model.getAvgDailyIncreaseWeekend() != null) {
            existing.setAvgDailyIncreaseWeekend(model.getAvgDailyIncreaseWeekend());
        }
        return modelRepository.save(existing);
    }
    
    @Override
    public UsagePatternModel getModelForBin(Long binId) {
        if (binId == null) {
            throw new BadRequestException("Bin ID cannot be null");
        }
        
        Bin bin = binRepository.findById(binId)
                .orElseThrow(() -> new ResourceNotFoundException("Bin not found"));
        
        @SuppressWarnings("null")
        UsagePatternModel result = modelRepository.findTop1ByBinOrderByLastUpdatedDesc(bin)
                .orElseThrow(() -> new ResourceNotFoundException("No model found for bin"));
        return result;
    }
    
    @Override
    public List<UsagePatternModel> getAllModels() {
        return modelRepository.findAll();
    }
}