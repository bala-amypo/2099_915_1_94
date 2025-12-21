package com.example.demo.service.impl;

import com.example.demo.exception.*;
import com.example.demo.model.*;
import com.example.demo.repository.*;
import com.example.demo.service.BinService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BinServiceImpl implements BinService {

    private final BinRepository binRepository;
    private final ZoneRepository zoneRepository;

    public BinServiceImpl(BinRepository binRepository,
                          ZoneRepository zoneRepository) {
        this.binRepository = binRepository;
        this.zoneRepository = zoneRepository;
    }

    public Bin createBin(Bin bin) {
        if (bin.getCapacityLiters() <= 0)
            throw new BadRequestException("capacity");

        Zone zone = zoneRepository.findById(bin.getZone().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Zone not found"));

        if (!zone.getActive())
            throw new BadRequestException("inactive");

        return binRepository.save(bin);
    }

    public Bin updateBin(Long id, Bin bin) {
        Bin existing = getBinById(id);
        existing.setLocationDescription(bin.getLocationDescription());
        return binRepository.save(existing);
    }

    public Bin getBinById(Long id) {
        return binRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Bin not found"));
    }

    public List<Bin> getAllBins() {
        return binRepository.findAll();
    }

    public void deactivateBin(Long id) {
        Bin bin = getBinById(id);
        bin.setActive(false);
        binRepository.save(bin);
    }
}
