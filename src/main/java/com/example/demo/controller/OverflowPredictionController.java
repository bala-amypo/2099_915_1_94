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

    @PostMapping("/bin/{binId}")
    public OverflowPrediction generatePrediction(@PathVariable Long binId) {
        return predictionService.generatePrediction(binId);
    }

    @GetMapping("/zone/{zoneId}")
    public List<OverflowPrediction> getPredictionsForZone(@PathVariable Long zoneId) {
        return predictionService.getLatestPredictionsForZone(zoneId);
    }
}
