package com.example.demo.service.impl;

import com.example.demo.model.Zone; // ✅ correct import
import com.example.demo.repository.ZoneRepository;
import com.example.demo.service.ZoneService;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ZoneServiceImpl implements ZoneService {

    private final ZoneRepository zoneRepository;

    public ZoneServiceImpl(ZoneRepository zoneRepository) {
        this.zoneRepository = zoneRepository;
    }

    @Override
    public List<Zone> getAllZones() {
        return zoneRepository.findAll();
    }

    @Override
    public Zone getZoneByName(String zoneName) {
        return zoneRepository.findByZoneName(zoneName); // ✅ this will work now
    }

    @Override
    public Zone saveZone(Zone zone) {
        return zoneRepository.save(zone);
    }
@Override
public Zone getZoneById(Long id) {
    return zoneRepository.findById(id).orElse(null);
}
`
    // Add other service methods if needed
}
