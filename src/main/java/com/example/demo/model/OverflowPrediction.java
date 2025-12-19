package com.example.demo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "overflow_predictions")
public class OverflowPrediction {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bin_id", nullable = false)
    private Bin bin;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "model_id")
    private UsagePatternModel modelUsed;
    
    @NotNull
    private Integer daysUntilFull;
    
    @NotNull
    private LocalDate predictedFullDate;
    
    private LocalDateTime createdAt;
    
    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }
    
    public OverflowPrediction() {}
    
    public OverflowPrediction(Bin bin, UsagePatternModel modelUsed, Integer daysUntilFull, LocalDate predictedFullDate) {
        this.bin = bin;
        this.modelUsed = modelUsed;
        this.daysUntilFull = daysUntilFull;
        this.predictedFullDate = predictedFullDate;
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
    
    public UsagePatternModel getModelUsed() {
        return modelUsed;
    }
    
    public void setModelUsed(UsagePatternModel modelUsed) {
        this.modelUsed = modelUsed;
    }
    
    public Integer getDaysUntilFull() {
        return daysUntilFull;
    }
    
    public void setDaysUntilFull(Integer daysUntilFull) {
        this.daysUntilFull = daysUntilFull;
    }
    
    public LocalDate getPredictedFullDate() {
        return predictedFullDate;
    }
    
    public void setPredictedFullDate(LocalDate predictedFullDate) {
        this.predictedFullDate = predictedFullDate;
    }
    
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}