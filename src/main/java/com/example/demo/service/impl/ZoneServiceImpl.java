package com.example.demo.service.impl;

import com.example.demo.exception.*;
import com.example.demo.model.Zone;
import com.example.demo.repository.ZoneRepository;
import com.example.demo.service.ZoneService;

import java.util.List;

public class ZoneServiceImpl implements ZoneService {

    private final ZoneRepository zoneRepository;

    public ZoneServiceImpl(ZoneRepository zoneRepository) {
        this.zoneRepository = zoneRepository;
    }

    public Zone createZone(Zone zone) {
        zone.setActive(true);
        return zoneRepository.save(zone);
    }

    public Zone updateZone(Long id, Zone zone) {
        Zone existing = getZoneById(id);
        existing.setDescription(zone.getDescription());
        return zoneRepository.save(existing);
    }

    public Zone getZoneById(Long id) {
        return zoneRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Zone not found"));
    }

    public List<Zone> getAllZones() {
        return null;
    }

    public void deactivateZone(Long id) {
        Zone zone = getZoneById(id);
        zone.setActive(false);
        zoneRepository.save(zone);
    }
}
