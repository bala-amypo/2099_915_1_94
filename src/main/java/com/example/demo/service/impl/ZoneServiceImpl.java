package com.example.demo.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.model.Zone;
import com.example.demo.repository.ZoneRepository;
import com.example.demo.service.ZoneService;

@Service
public class ZoneServiceImpl implements ZoneService {

    private final ZoneRepository zoneRepository;

    public ZoneServiceImpl(ZoneRepository zoneRepository) {
        this.zoneRepository = zoneRepository;
    }

    @Override
    public Zone create(Zone zone) {
        zone.setActive(true);
        return zoneRepository.save(zone);
    }

    @Override
    public Zone update(Long id, Zone zone) {

        Zone existing = getById(id);

        existing.setZoneName(zone.getZoneName());
        existing.setDescription(zone.getDescription());

        return zoneRepository.save(existing);
    }

    @Override
    public Zone getById(Long id) {
        return zoneRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Zone not found: " + id));
    }

    @Override
    public List<Zone> getAll() {
        return zoneRepository.findAll();
    }

    @Override
    public void deactivate(Long id) {
        Zone zone = getById(id);
        zone.setActive(false);
        zoneRepository.save(zone);
    }
}
