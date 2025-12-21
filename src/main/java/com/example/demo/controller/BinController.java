package com.example.demo.controller;

import com.example.demo.model.Bin;
import com.example.demo.service.BinService;

import java.util.List;

public class BinController {

    private final BinService binService;

    public BinController(BinService binService) {
        this.binService = binService;
    }

    public Bin createBin(Bin bin) {
        return binService.createBin(bin);
    }

    public Bin updateBin(Long id, Bin bin) {
        return binService.updateBin(id, bin);
    }

    public Bin getBin(Long id) {
        return binService.getBinById(id);
    }

    public List<Bin> getAllBins() {
        return binService.getAllBins();
    }

    public void deactivateBin(Long id) {
        binService.deactivateBin(id);
    }
}
