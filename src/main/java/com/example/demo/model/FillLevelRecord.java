package com.example.demo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Table(name = "fill_level_records")
public class FillLevelRecord {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bin_id", nullable = false)
    private Bin bin;
    
    @DecimalMin("0.0")
    @DecimalMax("100.0")
    @NotNull
    private Double fillPercentage;
    
    @NotNull
    private LocalDateTime recordedAt;
    
    public FillLevelRecord() {}
    
    public FillLevelRecord(Bin bin, Double fillPercentage, LocalDateTime recordedAt) {
        this.bin = bin;
        this.fillPercentage = fillPercentage;
        this.recordedAt = recordedAt;
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
    
    public Double getFillPercentage() {
        return fillPercentage;
    }
    
    public void setFillPercentage(Double fillPercentage) {
        this.fillPercentage = fillPercentage;
    }
    
    public LocalDateTime getRecordedAt() {
        return recordedAt;
    }
    
    public void setRecordedAt(LocalDateTime recordedAt) {
        this.recordedAt = recordedAt;
    }
}