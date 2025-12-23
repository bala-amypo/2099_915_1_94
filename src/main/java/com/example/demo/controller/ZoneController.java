package com.example.demo.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.example.demo.model.Zone;
import com.example.demo.service.ZoneService;

@RestController
@RequestMapping("/api/zones")
public class ZoneController {

    private final ZoneService zoneService;

    public ZoneController(ZoneService zoneService) {
        this.zoneService = zoneService;
    }

    @PostMapping
    public Zone create(@RequestBody Zone zone) {
        return zoneService.create(zone);
    }

    @PutMapping("/{id}")
    public Zone update(@PathVariable Long id,
                       @RequestBody Zone zone) {
        return zoneService.update(id, zone);
    }

    @GetMapping("/{id}")
    public Zone getById(@PathVariable Long id) {
        return zoneService.getById(id);
    }

    @GetMapping
    public List<Zone> getAll() {
        return zoneService.getAll();
    }

    @PutMapping("/{id}/deactivate")
    public void deactivate(@PathVariable Long id) {
        zoneService.deactivate(id);
    }
}
