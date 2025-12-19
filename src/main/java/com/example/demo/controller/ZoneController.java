package com.example.demo.controller;

import com.example.demo.model.Zone;
import com.example.demo.service.ZoneService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/zones")
@CrossOrigin(origins = "*")
public class ZoneController {
    
    private final ZoneService zoneService;
    
    public ZoneController(ZoneService zoneService) {
        this.zoneService = zoneService;
    }
    
    @PostMapping
    public ResponseEntity<Zone> createZone(@RequestBody Zone zone) {
        Zone created = zoneService.createZone(zone);
        return ResponseEntity.ok(created);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Zone> getZone(@PathVariable Long id) {
        Zone zone = zoneService.getZoneById(id);
        return ResponseEntity.ok(zone);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Zone> updateZone(@PathVariable Long id, @RequestBody Zone zone) {
        Zone updated = zoneService.updateZone(id, zone);
        return ResponseEntity.ok(updated);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deactivateZone(@PathVariable Long id) {
        zoneService.deactivateZone(id);
        return ResponseEntity.ok().build();
    }
    
    @GetMapping
    public ResponseEntity<List<Zone>> getAllZones() {
        List<Zone> zones = zoneService.getAllZones();
        return ResponseEntity.ok(zones);
    }
}