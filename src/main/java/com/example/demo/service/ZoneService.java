package com.example.demo.service;

import java.util.List;
import com.example.demo.model.Zone;

public interface ZoneService {

    Zone create(Zone zone);

    Zone update(Long id, Zone zone);

    Zone getById(Long id);

    List<Zone> getAll();

    void deactivate(Long id);
}
