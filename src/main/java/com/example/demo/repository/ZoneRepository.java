package com.example.demo.repository;

import com.example.demo.entity.Zone;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface ZoneRepository extends JpaRepository<Zone, Long> {
    // The method name must match the entity field exactly
    Optional<Zone> findByName(String name);
}
