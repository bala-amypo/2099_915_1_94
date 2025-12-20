package com.example.demo.service;

import com.example.demo.model.Zone;

import java.util.List;

public interface ZoneService {

    Zone createZone(Zone zone);

    Zone getZoneById(Long id);

    Zone getZoneByName(String zoneName);

    List<Zone> getAllZones();
}
