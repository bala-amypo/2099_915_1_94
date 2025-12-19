package com.example.demo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.demo.model.UsagePatternModel;
import com.example.demo.service.UsagePatternModelService;

import java.util.List;

@RestController
@RequestMapping("/api/usage-pattern")
@CrossOrigin(origins = "*")
public class UsagePatternModelController {

    private final UsagePatternModelService service;

    public UsagePatternModelController(UsagePatternModelService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<UsagePatternModel> save(@RequestBody UsagePatternModel model) {
        return ResponseEntity.ok(service.createModel(model));
    }

    @GetMapping("/bin/{binId}")
    public ResponseEntity<UsagePatternModel> getLatest(@PathVariable Long binId) {
        return ResponseEntity.ok(service.getModelForBin(binId));
    }

    @GetMapping
    public ResponseEntity<List<UsagePatternModel>> getAllModels() {
        return ResponseEntity.ok(service.getAllModels());
    }
}
