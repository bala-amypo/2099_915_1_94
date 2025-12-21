package com.example.demo.service.impl;

import com.example.demo.entity.Bin;
import com.example.demo.entity.Zone;
import com.example.demo.repository.BinRepository;
import com.example.demo.service.BinService;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class BinServiceImpl implements BinService {

    private final BinRepository binRepository;

    public BinServiceImpl(BinRepository binRepository) {
        this.binRepository = binRepository;
    }

    @Override
    public Bin saveBin(Bin bin) {
        return binRepository.save(bin);
    }

    @Override
    public List<Bin> getBinsByZone(Zone zone) {
        return binRepository.findByZone(zone);
    }
}
