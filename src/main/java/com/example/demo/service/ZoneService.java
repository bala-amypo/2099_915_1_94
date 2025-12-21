package com.example.demo.service;

import com.example.demo.model.*;
import java.util.List;

public interface ZoneService {
    Zone createZone(Zone zone);
    Zone getZoneById(Long id);
    Zone updateZone(Long id, Zone zone);
    void deactivateZone(Long id);
}
