package com.example.demo.service.impl;

import com.example.demo.exception.*;
import com.example.demo.model.*;
import com.example.demo.repository.*;
import com.example.demo.service.FillLevelRecordService;

import java.time.LocalDateTime;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class FillLevelRecordServiceImpl implements FillLevelRecordService {

    private final FillLevelRecordRepository recordRepository;
    private final BinRepository binRepository;

    public FillLevelRecordServiceImpl(
            FillLevelRecordRepository recordRepository,
            BinRepository binRepository) {
        this.recordRepository = recordRepository;
        this.binRepository = binRepository;
    }

    public FillLevelRecord createRecord(FillLevelRecord record) {
        Bin bin = binRepository.findById(record.getBin().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Bin not found"));

        if (!bin.getActive())
            throw new BadRequestException("inactive bin");

        if (record.getRecordedAt().isAfter(LocalDateTime.now()))
            throw new BadRequestException("future");

        return recordRepository.save(record);
    }

    public FillLevelRecord getRecordById(Long id) {
        return recordRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Record not found"));
    }

    public List<FillLevelRecord> getRecentRecords(Long binId, int limit) {
        Bin bin = binRepository.findById(binId)
                .orElseThrow(() -> new ResourceNotFoundException("Bin not found"));

        List<FillLevelRecord> list =
                recordRepository.findByBinOrderByRecordedAtDesc(bin);

        return list.subList(0, Math.min(limit, list.size()));
    }
}
