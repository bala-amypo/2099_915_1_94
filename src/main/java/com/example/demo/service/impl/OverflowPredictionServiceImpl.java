package com.example.demo.service.impl;

import com.example.demo.model.OverflowPrediction;
import com.example.demo.model.Zone;
import com.example.demo.repository.OverflowPredictionRepository;
import com.example.demo.service.OverflowPredictionService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OverflowPredictionServiceImpl implements OverflowPredictionService {

    private final OverflowPredictionRepository repository;

    public OverflowPredictionServiceImpl(OverflowPredictionRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<OverflowPrediction> getLatestPredictionsForZone(Zone zone) {
        return repository.findLatestPredictionsForZone(zone);
    }

    @Override
    public OverflowPrediction savePrediction(OverflowPrediction prediction) {
        return repository.save(prediction);
    }
}
