package com.example.demo.controller;

import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.demo.model.FillLevelRecord;
import com.example.demo.service.FillLevelRecordService;

@RestController
@RequestMapping("/api/fill-level")
@CrossOrigin(origins = "*")
public class FillLevelRecordController {

    private final FillLevelRecordService service;

    public FillLevelRecordController(FillLevelRecordService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<FillLevelRecord> save(@RequestBody FillLevelRecord record) {
        return ResponseEntity.ok(service.createRecord(record));
    }

    @GetMapping("/bin/{binId}")
    public ResponseEntity<List<FillLevelRecord>> getByBin(@PathVariable Long binId) {
        return ResponseEntity.ok(service.getRecentRecords(binId, 10));
    }

    @GetMapping
    public ResponseEntity<List<FillLevelRecord>> getAllRecords() {
        return ResponseEntity.ok(service.getAllRecords());
    }
}
