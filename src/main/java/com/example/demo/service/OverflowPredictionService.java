package com.example.demo.service;

import com.example.demo.model.OverflowPrediction;

import java.util.List;

public interface OverflowPredictionService {

    OverflowPrediction predictOverflow(Long binId);

    List<OverflowPrediction> getPredictionsForZone(Long zoneId);
}
