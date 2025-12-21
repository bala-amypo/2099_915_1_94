package com.example.demo.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class UsagePatternModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double avgDailyIncreaseWeekday;
    private Double avgDailyIncreaseWeekend;

    private LocalDateTime lastUpdated = LocalDateTime.now();

    @ManyToOne
    @JoinColumn(name = "bin_id")
    private Bin bin;
}
