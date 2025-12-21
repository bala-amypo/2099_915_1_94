package com.example.demo.controller;

import com.example.demo.model.UsagePatternModel;
import com.example.demo.service.UsagePatternModelService;

import java.util.List;

public class UsagePatternModelController {

    private final UsagePatternModelService modelService;

    public UsagePatternModelController(UsagePatternModelService modelService) {
        this.modelService = modelService;
    }

    public UsagePatternModel createModel(UsagePatternModel model) {
        return modelService.createModel(model);
    }

    public UsagePatternModel updateModel(Long id, UsagePatternModel model) {
        return modelService.updateModel(id, model);
    }

    public UsagePatternModel getModelForBin(Long binId) {
        return modelService.getModelForBin(binId);
    }

    public List<UsagePatternModel> getAllModels() {
        return modelService.getAllModels();
    }
}
