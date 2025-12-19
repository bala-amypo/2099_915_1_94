package com.example.demo.service.impl;

import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Bin;
import com.example.demo.model.FillLevelRecord;
import com.example.demo.repository.BinRepository;
import com.example.demo.repository.FillLevelRecordRepository;
import com.example.demo.service.FillLevelRecordService;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class FillLevelRecordServiceImpl implements FillLevelRecordService {
    
    private final FillLevelRecordRepository recordRepository;
    private final BinRepository binRepository;
    
    public FillLevelRecordServiceImpl(FillLevelRecordRepository recordRepository, BinRepository binRepository) {
        this.recordRepository = recordRepository;
        this.binRepository = binRepository;
    }
    
    @Override
    public FillLevelRecord createRecord(FillLevelRecord record) {
        if (record.getBin() == null || record.getBin().getId() == null) {
            throw new BadRequestException("Bin is required");
        }
        
        @SuppressWarnings("null")
        Bin bin = binRepository.findById(record.getBin().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Bin not found"));
        
        if (!bin.getActive()) {
            throw new BadRequestException("Cannot create record for inactive bin");
        }
        
        if (record.getRecordedAt().isAfter(LocalDateTime.now())) {
            throw new BadRequestException("Recorded time cannot be in the future");
        }
        
        return recordRepository.save(record);
    }
    
    @Override
    public FillLevelRecord getRecordById(Long id) {
        if (id == null) {
            throw new BadRequestException("ID cannot be null");
        }
        return recordRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Record not found with id: " + id));
    }
    
    @Override
    public List<FillLevelRecord> getRecentRecords(Long binId, int limit) {
        if (binId == null) {
            throw new BadRequestException("Bin ID cannot be null");
        }
        
        Bin bin = binRepository.findById(binId)
                .orElseThrow(() -> new ResourceNotFoundException("Bin not found"));
        
        List<FillLevelRecord> records = recordRepository.findByBinOrderByRecordedAtDesc(bin);
        return records.size() > limit ? records.subList(0, limit) : records;
    }
    
    @Override
    public List<FillLevelRecord> getAllRecords() {
        return recordRepository.findAll();
    }
}