package com.example.demo.controller;

import com.example.demo.entity.Zone;
import com.example.demo.repository.ZoneRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/zones")
public class ZoneController {

    private final ZoneRepository zoneRepository;

    public ZoneController(ZoneRepository zoneRepository) {
        this.zoneRepository = zoneRepository;
    }

    @PostMapping
    public ResponseEntity<?> createZone(@RequestBody Zone zone) {
        try {
            if (zone.getBins() != null) {
                zone.getBins().forEach(bin -> bin.setZone(zone)); // link bins to this zone
            }
            Zone savedZone = zoneRepository.save(zone);
            return ResponseEntity.ok(savedZone);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ErrorResponse("Internal server error", 500));
        }
    }

    // Optional GET
    @GetMapping("/{id}")
    public ResponseEntity<?> getZone(@PathVariable Long id) {
        return zoneRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(new ErrorResponse("Zone not found", 404)));
    }

    // Error response class
    static class ErrorResponse {
        private String message;
        private int status;

        public ErrorResponse(String message, int status) {
            this.message = message;
            this.status = status;
        }

        public String getMessage() { return message; }
        public int getStatus() { return status; }
    }
}
