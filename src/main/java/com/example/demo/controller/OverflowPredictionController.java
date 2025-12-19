package com.example.demo.controller;

import com.example.demo.model.OverflowPrediction;
import com.example.demo.service.OverflowPredictionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/overflow")
@CrossOrigin(origins = "*")
public class OverflowPredictionController {

    private final OverflowPredictionService service;

    public OverflowPredictionController(OverflowPredictionService service) {
        this.service = service;
    }

    @PostMapping("/predict/{binId}")
    public ResponseEntity<OverflowPrediction> predict(@PathVariable Long binId) {
        return ResponseEntity.ok(service.generatePrediction(binId));
    }

    @GetMapping("/zone/{zoneId}")
    public ResponseEntity<List<OverflowPrediction>> getZonePredictions(@PathVariable Long zoneId) {
        return ResponseEntity.ok(service.getLatestPredictionsForZone(zoneId));
    }

    @GetMapping
    public ResponseEntity<List<OverflowPrediction>> getAllPredictions() {
        return ResponseEntity.ok(service.getAllPredictions());
    }

    @GetMapping("/{id}")
    public ResponseEntity<OverflowPrediction> getPrediction(@PathVariable Long id) {
        return ResponseEntity.ok(service.getPredictionById(id));
    }
}
