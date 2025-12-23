package com.example.demo.service.impl;

import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Bin;
import com.example.demo.model.Zone;
import com.example.demo.repository.BinRepository;
import com.example.demo.repository.ZoneRepository;
import com.example.demo.service.BinService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service   // ⭐ VERY IMPORTANT
public class BinServiceImpl implements BinService {

    private final BinRepository binRepository;
    private final ZoneRepository zoneRepository;

    public BinServiceImpl(BinRepository binRepository,
                          ZoneRepository zoneRepository) {
        this.binRepository = binRepository;
        this.zoneRepository = zoneRepository;
    }

    @Override
    public Bin createBin(Bin bin) {

        if (bin.getCapacityLiters() == null || bin.getCapacityLiters() <= 0) {
            throw new BadRequestException("Bin capacity must be greater than zero");
        }

        if (bin.getZone() == null || bin.getZone().getId() == null) {
            throw new BadRequestException("Zone is required");
        }

        Zone zone = zoneRepository.findById(bin.getZone().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Zone not found"));

        if (!zone.getActive()) {
            throw new BadRequestException("Cannot assign bin to inactive zone");
        }

        bin.setZone(zone);
        bin.setActive(true);

        return binRepository.save(bin);
    }

    @Override
    public Bin getBinById(Long id) {
        return binRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Bin not found"));
    }

    @Override
    public List<Bin> getAllBins() {
        return binRepository.findAll();
    }

    @Override
    public Bin updateBin(Long id, Bin updatedBin) {

        Bin existing = getBinById(id);

        if (updatedBin.getIdentifier() != null) {
            existing.setIdentifier(updatedBin.getIdentifier());
        }

        if (updatedBin.getCapacityLiters() != null) {
            if (updatedBin.getCapacityLiters() <= 0) {
                throw new BadRequestException("Capacity must be positive");
            }
            existing.setCapacityLiters(updatedBin.getCapacityLiters());
        }

        if (updatedBin.getLatitude() != null) {
            existing.setLatitude(updatedBin.getLatitude());
        }

        if (updatedBin.getLongitude() != null) {
            existing.setLongitude(updatedBin.getLongitude());
        }

        if (updatedBin.getLocationDescription() != null) {
            existing.setLocationDescription(updatedBin.getLocationDescription());
        }

        if (updatedBin.getZone() != null && updatedBin.getZone().getId() != null) {
            Zone zone = zoneRepository.findById(updatedBin.getZone().getId())
                    .orElseThrow(() -> new ResourceNotFoundException("Zone not found"));

            if (!zone.getActive()) {
                throw new BadRequestException("Cannot move bin to inactive zone");
            }

            existing.setZone(zone);
        }

        return binRepository.save(existing);
    }

    @Override
    public void deactivateBin(Long id) {
        Bin bin = getBinById(id);
        bin.setActive(false);
        binRepository.save(bin);
    }
}
