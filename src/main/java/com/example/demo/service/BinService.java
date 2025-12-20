package com.example.demo.service;

import com.example.demo.model.Bin;

import java.util.List;

public interface BinService {

    Bin createBin(Bin bin);

    Bin getBinById(Long id);

    Bin getBinByIdentifier(String identifier);

    List<Bin> getActiveBinsByZone(Long zoneId);
}
