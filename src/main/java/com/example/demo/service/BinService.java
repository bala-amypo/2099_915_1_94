package com.example.demo.service;

import com.example.demo.entity.Bin;
import com.example.demo.entity.Zone;
import java.util.List;

public interface BinService {
    Bin saveBin(Bin bin);
    List<Bin> getBinsByZone(Zone zone);
}
