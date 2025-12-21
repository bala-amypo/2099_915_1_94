package com.example.demo.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Bin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String identifier;
    private Double capacityLiters;
    private Double latitude;
    private Double longitude;
    private String locationDescription;
    private Boolean active = true;

    @ManyToOne
    @JoinColumn(name = "zone_id")
    private Zone zone;
}
