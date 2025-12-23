package com.example.demo.controller;

// ✅ Spring annotations
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

// ✅ Java utilities
import java.util.List;

// ✅ Project classes
import com.example.demo.model.OverflowPrediction;
import com.example.demo.service.OverflowPredictionService;

@RestController
@RequestMapping("/api/predictions")
public class OverflowPredictionController {

    private final OverflowPredictionService predictionService;

    public OverflowPredictionController(OverflowPredictionService predictionService) {
        this.predictionService = predictionService;
    }

    @PostMapping("/generate/{binId}")
    public OverflowPrediction generatePrediction(@PathVariable Long binId) {
        return predictionService.generatePrediction(binId);
    }

    @GetMapping("/zone/{zoneId}/latest")
    public List<OverflowPrediction> getLatestPredictions(@PathVariable Long zoneId) {
        return predictionService.getLatestPredictionsForZone(zoneId);
    }
}
