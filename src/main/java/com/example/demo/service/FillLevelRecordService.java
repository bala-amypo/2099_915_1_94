package com.example.demo.service;

import com.example.demo.model.FillLevelRecord;

public interface FillLevelRecordService {

    FillLevelRecord recordFillLevel(Long binId, Double fillPercentage);

    FillLevelRecord getLatestRecord(Long binId);
}
