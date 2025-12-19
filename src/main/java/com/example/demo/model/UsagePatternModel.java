package com.example.demo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import java.time.LocalDateTime;

@Entity
@Table(name = "usage_pattern_models")
public class UsagePatternModel {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bin_id", nullable = false)
    private Bin bin;
    
    @PositiveOrZero
    @NotNull
    private Double avgDailyIncreaseWeekday;
    
    @PositiveOrZero
    @NotNull
    private Double avgDailyIncreaseWeekend;
    
    private LocalDateTime lastUpdated;
    
    @PrePersist
    @PreUpdate
    protected void onUpdate() {
        lastUpdated = LocalDateTime.now();
    }
    
    public UsagePatternModel() {}
    
    public UsagePatternModel(Bin bin, Double avgDailyIncreaseWeekday, Double avgDailyIncreaseWeekend) {
        this.bin = bin;
        this.avgDailyIncreaseWeekday = avgDailyIncreaseWeekday;
        this.avgDailyIncreaseWeekend = avgDailyIncreaseWeekend;
    }
    
    // Getters and Setters
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public Bin getBin() {
        return bin;
    }
    
    public void setBin(Bin bin) {
        this.bin = bin;
    }
    
    public Double getAvgDailyIncreaseWeekday() {
        return avgDailyIncreaseWeekday;
    }
    
    public void setAvgDailyIncreaseWeekday(Double avgDailyIncreaseWeekday) {
        this.avgDailyIncreaseWeekday = avgDailyIncreaseWeekday;
    }
    
    public Double getAvgDailyIncreaseWeekend() {
        return avgDailyIncreaseWeekend;
    }
    
    public void setAvgDailyIncreaseWeekend(Double avgDailyIncreaseWeekend) {
        this.avgDailyIncreaseWeekend = avgDailyIncreaseWeekend;
    }
    
    public LocalDateTime getLastUpdated() {
        return lastUpdated;
    }
    
    public void setLastUpdated(LocalDateTime lastUpdated) {
        this.lastUpdated = lastUpdated;
    }
}