package com.example.demo.controller;

import com.example.demo.model.OverflowPrediction;
import com.example.demo.model.Zone;
import com.example.demo.service.OverflowPredictionService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/overflow")
public class OverflowPredictionController {

    private final OverflowPredictionService service;

    public OverflowPredictionController(OverflowPredictionService service) {
        this.service = service;
    }

    @GetMapping("/zone/{zoneId}")
    public List<OverflowPrediction> getPredictionsByZone(@PathVariable Long zoneId) {
        Zone zone = new Zone();
        zone.setId(zoneId);
        return service.getLatestPredictionsForZone(zone);
    }

    @PostMapping("/save")
    public OverflowPrediction savePrediction(@RequestBody OverflowPrediction prediction) {
        return service.savePrediction(prediction);
    }
}
