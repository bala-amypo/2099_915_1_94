package com.example.demo.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class FillLevelRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double fillPercentage;
    private LocalDateTime recordedAt;

    @ManyToOne
    @JoinColumn(name = "bin_id")
    private Bin bin;
}
