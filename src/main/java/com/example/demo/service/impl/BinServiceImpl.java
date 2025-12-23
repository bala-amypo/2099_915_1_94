package com.example.demo.service.impl;

import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Bin;
import com.example.demo.model.Zone;
import com.example.demo.repository.BinRepository;
import com.example.demo.repository.ZoneRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BinServiceImpl {

    private final BinRepository binRepository;
    private final ZoneRepository zoneRepository;

    public BinServiceImpl(BinRepository binRepository, ZoneRepository zoneRepository) {
        this.binRepository = binRepository;
        this.zoneRepository = zoneRepository;
    }

    public Bin createBin(Bin bin) {
        if (bin.getCapacityLiters() == null || bin.getCapacityLiters() <= 0) {
            throw new BadRequestException("Capacity must be positive");
        }

        Zone zone = zoneRepository.findById(bin.getZone().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Zone not found"));

        if (!zone.getActive()) {
            throw new BadRequestException("Zone is inactive");
        }

        bin.setZone(zone);
        bin.setActive(true);
        return binRepository.save(bin);
    }

    public Bin getBinById(Long id) {
        return binRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Bin not found"));
    }

    public List<Bin> getAllBins() {
        return binRepository.findAll();
    }

    public Bin updateBin(Long id, Bin update) {
        Bin existing = getBinById(id);

        if (update.getLocationDescription() != null) {
            existing.setLocationDescription(update.getLocationDescription());
        }
        return binRepository.save(existing);
    }

    public void deactivateBin(Long id) {
        Bin bin = getBinById(id);
        bin.setActive(false);
        binRepository.save(bin);
    }
}
