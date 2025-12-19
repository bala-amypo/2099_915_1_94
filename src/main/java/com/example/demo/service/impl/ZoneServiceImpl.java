package com.example.demo.service.impl;

import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Zone;
import com.example.demo.repository.ZoneRepository;
import com.example.demo.service.ZoneService;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@SuppressWarnings("null")
public class ZoneServiceImpl implements ZoneService {
    
    private final ZoneRepository zoneRepository;
    
    public ZoneServiceImpl(ZoneRepository zoneRepository) {
        this.zoneRepository = zoneRepository;
    }
    
    @Override
    public Zone createZone(Zone zone) {
        if (zone.getActive() == null) {
            zone.setActive(true);
        }
        return zoneRepository.save(zone);
    }
    
    @Override
    public Zone getZoneById(Long id) {
        if (id == null) {
            throw new BadRequestException("ID cannot be null");
        }
        return zoneRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Zone not found with id: " + id));
    }
    
    @Override
    public Zone updateZone(Long id, Zone zone) {
        Zone existing = getZoneById(id);
        if (zone.getZoneName() != null) {
            existing.setZoneName(zone.getZoneName());
        }
        if (zone.getDescription() != null) {
            existing.setDescription(zone.getDescription());
        }
        return zoneRepository.save(existing);
    }
    
    @Override
    public void deactivateZone(Long id) {
        if (id == null) {
            throw new BadRequestException("ID cannot be null");
        }
        Zone zone = getZoneById(id);
        zone.setActive(false);
        zoneRepository.save(zone);
    }
    
    @Override
    public List<Zone> getAllZones() {
        return zoneRepository.findAll();
    }
}