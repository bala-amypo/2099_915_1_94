package com.example.demo.controller;

import com.example.demo.model.UsagePatternModel;
import com.example.demo.service.UsagePatternModelService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usage-models")
public class UsagePatternModelController {

    private final UsagePatternModelService modelService;

    public UsagePatternModelController(UsagePatternModelService modelService) {
        this.modelService = modelService;
    }

    @PostMapping("/{binId}")
    public UsagePatternModel generateModel(@PathVariable Long binId) {
        return modelService.generateOrUpdateModel(binId);
    }
}
