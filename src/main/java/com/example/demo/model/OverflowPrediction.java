package com.example.demo.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
public class OverflowPrediction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer daysUntilFull;
    private LocalDate predictedFullDate;

    @ManyToOne
    @JoinColumn(name = "bin_id")
    private Bin bin;

    @Transient
    private UsagePatternModel modelUsed;
}
