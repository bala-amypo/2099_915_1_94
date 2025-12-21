package com.example.demo.controller;

import com.example.demo.entity.Bin;
import com.example.demo.entity.Zone;
import com.example.demo.repository.BinRepository;
import com.example.demo.repository.ZoneRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/bins")
public class BinController {

    private final BinRepository binRepository;
    private final ZoneRepository zoneRepository;

    public BinController(BinRepository binRepository, ZoneRepository zoneRepository) {
        this.binRepository = binRepository;
        this.zoneRepository = zoneRepository;
    }

    @PostMapping
    public ResponseEntity<?> createBin(@RequestBody BinRequest binRequest) {
        try {
            Bin bin = new Bin();
            bin.setIdentifier(binRequest.getIdentifier());
            bin.setCapacityLiters(binRequest.getCapacityLiters());
            bin.setLatitude(binRequest.getLatitude());
            bin.setLongitude(binRequest.getLongitude());
            bin.setActive(binRequest.isActive());
            bin.setLocationDescription(binRequest.getLocationDescription());

            // Link to Zone if zoneId provided
            if (binRequest.getZoneId() != null) {
                Zone zone = zoneRepository.findById(binRequest.getZoneId())
                        .orElseThrow(() -> new RuntimeException("Zone not found"));
                bin.setZone(zone);
            }

            Bin savedBin = binRepository.save(bin);
            return ResponseEntity.ok(savedBin);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ErrorResponse("Internal server error", 500));
        }
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

    // Request DTO
    static class BinRequest {
        private String identifier;
        private int capacityLiters;
        private double latitude;
        private double longitude;
        private boolean active;
        private String locationDescription;
        private Long zoneId; // optional

        // Getters and Setters
        public String getIdentifier() { return identifier; }
        public void setIdentifier(String identifier) { this.identifier = identifier; }

        public int getCapacityLiters() { return capacityLiters; }
        public void setCapacityLiters(int capacityLiters) { this.capacityLiters = capacityLiters; }

        public double getLatitude() { return latitude; }
        public void setLatitude(double latitude) { this.latitude = latitude; }

        public double getLongitude() { return longitude; }
        public void setLongitude(double longitude) { this.longitude = longitude; }

        public boolean isActive() { return active; }
        public void setActive(boolean active) { this.active = active; }

        public String getLocationDescription() { return locationDescription; }
        public void setLocationDescription(String locationDescription) { this.locationDescription = locationDescription; }

        public Long getZoneId() { return zoneId; }
        public void setZoneId(Long zoneId) { this.zoneId = zoneId; }
    }
}
