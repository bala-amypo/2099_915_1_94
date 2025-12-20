package com.example.demo.controller;

import com.example.demo.model.FillLevelRecord;
import com.example.demo.service.FillLevelRecordService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/fill-levels")
public class FillLevelRecordController {

    private final FillLevelRecordService recordService;

    public FillLevelRecordController(FillLevelRecordService recordService) {
        this.recordService = recordService;
    }

    @PostMapping("/{binId}")
    public FillLevelRecord recordFillLevel(
            @PathVariable Long binId,
            @RequestParam Double fillPercentage) {

        return recordService.recordFillLevel(binId, fillPercentage);
    }

    @GetMapping("/latest/{binId}")
    public FillLevelRecord getLatestRecord(@PathVariable Long binId) {
        return recordService.getLatestRecord(binId);
    }
}
