package com.example.demo.controller;

import com.example.demo.model.FillLevelRecord;
import com.example.demo.service.FillLevelRecordService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/fill-records")
public class FillLevelRecordController {

    private final FillLevelRecordService recordService;

    public FillLevelRecordController(FillLevelRecordService recordService) {
        this.recordService = recordService;
    }

    @PostMapping
    public FillLevelRecord create(@RequestBody FillLevelRecord record) {
        return recordService.createRecord(record);
    }

    @GetMapping("/{id}")
    public FillLevelRecord get(@PathVariable Long id) {
        return recordService.getRecordById(id);
    }

    @GetMapping("/bin/{binId}/recent")
    public List<FillLevelRecord> recent(
            @PathVariable Long binId,
            @RequestParam int limit) {
        return recordService.getRecentRecords(binId, limit);
    }
}
