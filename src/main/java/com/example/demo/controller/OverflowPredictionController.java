package com.example.demo.controller;

import com.example.demo.model.OverflowPrediction;
import com.example.demo.service.OverflowPredictionService;

import java.util.List;

public class OverflowPredictionController {

    private final OverflowPredictionService predictionService;

    public OverflowPredictionController(OverflowPredictionService predictionService) {
        this.predictionService = predictionService;
    }

    public OverflowPrediction generatePrediction(Long binId) {
        return predictionService.generatePrediction(binId);
    }

    public List<OverflowPrediction> getPredictionsForZone(Long zoneId) {
        return predictionService.getLatestPredictionsForZone(zoneId);
    }
}
