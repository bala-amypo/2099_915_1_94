package com.example.demo.service.impl;

import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Bin;
import com.example.demo.model.FillLevelRecord;
import com.example.demo.repository.BinRepository;
import com.example.demo.repository.FillLevelRecordRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class FillLevelRecordServiceImpl {

    private final FillLevelRecordRepository recordRepository;
    private final BinRepository binRepository;

    public FillLevelRecordServiceImpl(FillLevelRecordRepository recordRepository,
                                      BinRepository binRepository) {
        this.recordRepository = recordRepository;
        this.binRepository = binRepository;
    }

    public FillLevelRecord createRecord(FillLevelRecord record) {
        Bin bin = binRepository.findById(record.getBin().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Bin not found"));

        if (!bin.getActive()) {
            throw new BadRequestException("Bin inactive");
        }

        if (record.getRecordedAt().isAfter(LocalDateTime.now())) {
            throw new BadRequestException("Future time not allowed");
        }

        record.setBin(bin);
        return recordRepository.save(record);
    }

    public FillLevelRecord getRecordById(Long id) {
        return recordRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Record not found"));
    }

    public List<FillLevelRecord> getRecentRecords(Long binId, int limit) {
        Bin bin = binRepository.findById(binId)
                .orElseThrow(() -> new ResourceNotFoundException("Bin not found"));

        return recordRepository.findByBinOrderByRecordedAtDesc(bin)
                .stream().limit(limit).toList();
    }
}
