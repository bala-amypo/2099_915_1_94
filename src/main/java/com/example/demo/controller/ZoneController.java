package com.example.demo.controller;

import com.example.demo.model.Zone;
import com.example.demo.service.ZoneService;

import java.util.List;

public class ZoneController {

    private final ZoneService zoneService;

    public ZoneController(ZoneService zoneService) {
        this.zoneService = zoneService;
    }

    public Zone createZone(Zone zone) {
        return zoneService.createZone(zone);
    }

    public Zone updateZone(Long id, Zone zone) {
        return zoneService.updateZone(id, zone);
    }

    public Zone getZone(Long id) {
        return zoneService.getZoneById(id);
    }

    public List<Zone> getAllZones() {
        return zoneService.getAllZones();
    }

    public void deactivateZone(Long id) {
        zoneService.deactivateZone(id);
    }
}
