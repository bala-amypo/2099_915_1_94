package com.example.demo.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.model.UsagePatternModel;
import com.example.demo.service.UsagePatternModelService;

@RestController
@RequestMapping("/api/models")
public class UsagePatternModelController {

    private final UsagePatternModelService usagePatternModelService;

    public UsagePatternModelController(
            UsagePatternModelService usagePatternModelService) {
        this.usagePatternModelService = usagePatternModelService;
    }

    // ---------------- CREATE ----------------
    @PostMapping
    public ResponseEntity<UsagePatternModel> createModel(
            @RequestBody UsagePatternModel model) {

        UsagePatternModel saved =
                usagePatternModelService.create(model);

        return ResponseEntity.ok(saved);
    }

    // ---------------- UPDATE ----------------
    @PutMapping("/{id}")
    public ResponseEntity<UsagePatternModel> updateModel(
            @PathVariable Long id,
            @RequestBody UsagePatternModel model) {

        UsagePatternModel updated =
                usagePatternModelService.update(id, model);

        return ResponseEntity.ok(updated);
    }

    // ---------------- GET ALL ----------------
    @GetMapping
    public ResponseEntity<List<UsagePatternModel>> getAllModels() {
        return ResponseEntity.ok(
                usagePatternModelService.getAll());
    }

    // ---------------- GET BY BIN ID ----------------
    @GetMapping("/bin/{binId}")
    public ResponseEntity<UsagePatternModel> getByBinId(
            @PathVariable Long binId) {

        return ResponseEntity.ok(
                usagePatternModelService.getByBinId(binId));
    }
}
