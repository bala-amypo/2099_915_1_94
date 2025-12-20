package com.example.demo.service;

import com.example.demo.model.Zone;

import java.util.List;

public interface ZoneService {
    List<Zone> getAllZones();
    Zone getZoneById(Long id);
    Zone getZoneByName(String name);  // Make sure this exists
    Zone createZone(Zone zone);
    Zone updateZone(Long id, Zone zoneDetails);
    void deleteZone(Long id);
}
