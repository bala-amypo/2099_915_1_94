package com.example.demo.controller;

import com.example.demo.model.Bin;
import com.example.demo.service.BinService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bins")
public class BinController {

    private final BinService binService;

    public BinController(BinService binService) {
        this.binService = binService;
    }

    @PostMapping
    public Bin createBin(@RequestBody Bin bin) {
        return binService.createBin(bin);
    }

    @GetMapping("/{id}")
    public Bin getBinById(@PathVariable Long id) {
        return binService.getBinById(id);
    }

    @GetMapping("/identifier/{identifier}")
    public Bin getByIdentifier(@PathVariable String identifier) {
        return binService.getBinByIdentifier(identifier);
    }

    @GetMapping("/zone/{zoneId}")
    public List<Bin> getActiveBinsByZone(@PathVariable Long zoneId) {
        return binService.getActiveBinsByZone(zoneId);
    }
}
