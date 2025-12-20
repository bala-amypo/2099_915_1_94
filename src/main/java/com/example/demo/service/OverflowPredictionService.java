package com.example.demo.service;

import com.example.demo.model.OverflowPrediction;
import com.example.demo.model.Zone;

import java.util.List;

public interface OverflowPredictionService {

    List<OverflowPrediction> getLatestPredictionsForZone(Zone zone);

    OverflowPrediction savePrediction(OverflowPrediction prediction);
}
