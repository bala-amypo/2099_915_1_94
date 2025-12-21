package com.example.demo.controller;

import com.example.demo.model.FillLevelRecord;
import com.example.demo.service.FillLevelRecordService;

import java.util.List;

public class FillLevelRecordController {

    private final FillLevelRecordService recordService;

    public FillLevelRecordController(FillLevelRecordService recordService) {
        this.recordService = recordService;
    }

    public FillLevelRecord createRecord(FillLevelRecord record) {
        return recordService.createRecord(record);
    }

    public FillLevelRecord getRecord(Long id) {
        return recordService.getRecordById(id);
    }

    public List<FillLevelRecord> getRecentRecords(Long binId, int limit) {
        return recordService.getRecentRecords(binId, limit);
    }
}
