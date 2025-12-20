package com.example.demo.controller;

import com.example.demo.model.OverflowPrediction;
import com.example.demo.service.OverflowPredictionService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/predictions")
public class OverflowPredictionController {

    private final OverflowPredictionService predictionService;

    public OverflowPredictionController(OverflowPredictionService predictionService) {
        this.predictionService = predictionService;
    }

    @PostMapping("/{binId}")
    public OverflowPrediction predict(@PathVariable Long binId) {
        return predictionService.predictOverflow(binId);
    }

    @GetMapping("/zone/{zoneId}")
    public List<OverflowPrediction> getPredictionsForZone(@PathVariable Long zoneId) {
        return predictionService.getPredictionsForZone(zoneId);
    }
}
