package com.example.demo.service.impl;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Zone;
import com.example.demo.repository.ZoneRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ZoneServiceImpl {

    private final ZoneRepository zoneRepository;

    public ZoneServiceImpl(ZoneRepository zoneRepository) {
        this.zoneRepository = zoneRepository;
    }

    public Zone createZone(Zone zone) {
        zone.setActive(true);
        return zoneRepository.save(zone);
    }

    public Zone getZoneById(Long id) {
        return zoneRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Zone not found"));
    }

    public List<Zone> getAllZones() {
        return zoneRepository.findAll();
    }

    public Zone updateZone(Long id, Zone update) {
        Zone zone = getZoneById(id);
        if (update.getDescription() != null) {
            zone.setDescription(update.getDescription());
        }
        return zoneRepository.save(zone);
    }

    public void deactivateZone(Long id) {
        Zone zone = getZoneById(id);
        zone.setActive(false);
        zoneRepository.save(zone);
    }
}
