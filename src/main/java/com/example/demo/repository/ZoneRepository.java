package com.example.demo.repository;

import java.util.*;
import com.example.demo.model.*;
@Repository
public interface ZoneRepository {
    Optional<Zone> findById(Long id);
    Optional<Zone> findByZoneName(String name);
    Zone save(Zone zone);
}
