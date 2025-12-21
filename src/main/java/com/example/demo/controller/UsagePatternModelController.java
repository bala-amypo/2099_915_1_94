package com.example.demo.controller;

import com.example.demo.model.UsagePatternModel;
import com.example.demo.service.UsagePatternModelService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/models")
public class UsagePatternModelController {

    private final UsagePatternModelService modelService;

    public UsagePatternModelController(UsagePatternModelService modelService) {
        this.modelService = modelService;
    }

    @PostMapping
    public UsagePatternModel create(@RequestBody UsagePatternModel model) {
        return modelService.createModel(model);
    }

    @PutMapping("/{id}")
    public UsagePatternModel update(
            @PathVariable Long id,
            @RequestBody UsagePatternModel model) {
        return modelService.updateModel(id, model);
    }

    @GetMapping("/bin/{binId}")
    public UsagePatternModel getForBin(@PathVariable Long binId) {
        return modelService.getModelForBin(binId);
    }

    @GetMapping
    public List<UsagePatternModel> getAll() {
        return modelService.getAllModels();
    }
}
