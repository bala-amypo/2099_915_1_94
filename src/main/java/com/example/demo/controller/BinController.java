package com.example.demo.controller;

import com.example.demo.model.Bin;
import com.example.demo.service.BinService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bins")
@CrossOrigin(origins = "*")
public class BinController {

    private final BinService service;

    public BinController(BinService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Bin> createBin(@RequestBody Bin bin) {
        return ResponseEntity.ok(service.createBin(bin));
    }

    @GetMapping
    public ResponseEntity<List<Bin>> getAllBins() {
        return ResponseEntity.ok(service.getAllBins());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Bin> getBin(@PathVariable Long id) {
        return ResponseEntity.ok(service.getBinById(id));
    }

    @GetMapping("/zone/{zoneId}")
    public ResponseEntity<List<Bin>> getBinsByZone(@PathVariable Long zoneId) {
        return ResponseEntity.ok(service.getAllBins());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Bin> updateBin(@PathVariable Long id, @RequestBody Bin bin) {
        return ResponseEntity.ok(service.updateBin(id, bin));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBin(@PathVariable Long id) {
        service.deactivateBin(id);
        return ResponseEntity.noContent().build();
    }
}
