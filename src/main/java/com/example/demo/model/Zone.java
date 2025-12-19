package com.example.demo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "zones")
public class Zone {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotBlank
    @Column(unique = true)
    private String zoneName;
    
    private String description;
    
    @Column(nullable = false)
    private Boolean active = true;
    
    public Zone() {}
    
    public Zone(String zoneName, String description) {
        this.zoneName = zoneName;
        this.description = description;
        this.active = true;
    }
    
    // Getters and Setters
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getZoneName() {
        return zoneName;
    }
    
    public void setZoneName(String zoneName) {
        this.zoneName = zoneName;
    }
    
    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    public Boolean getActive() {
        return active;
    }
    
    public void setActive(Boolean active) {
        this.active = active;
    }
}