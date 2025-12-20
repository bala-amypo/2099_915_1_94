package com.example.demo.repository;

import com.example.demo.model.Zone;  // ✅ correct import
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ZoneRepository extends JpaRepository<Zone, Long> {

    // Add this method if you need to find by zone name
    Zone findByZoneName(String zoneName);
}
