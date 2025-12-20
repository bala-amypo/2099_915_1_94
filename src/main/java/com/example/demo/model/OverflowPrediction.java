package com.example.demo.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class OverflowPrediction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "zone_id")
    private Zone zone;

    private double predictedLevel;

    private LocalDateTime timestamp;

    public OverflowPrediction() {}

    public OverflowPrediction(Zone zone, double predictedLevel, LocalDateTime timestamp) {
        this.zone = zone;
        this.predictedLevel = predictedLevel;
        this.timestamp = timestamp;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Zone getZone() { return zone; }
    public void setZone(Zone zone) { this.zone = zone; }

    public double getPredictedLevel() { return predictedLevel; }
    public void setPredictedLevel(double predictedLevel) { this.predictedLevel = predictedLevel; }

    public LocalDateTime getTimestamp() { return timestamp; }
    public void setTimestamp(LocalDateTime timestamp) { this.timestamp = timestamp; }
}
