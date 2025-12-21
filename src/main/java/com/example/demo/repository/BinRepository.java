package com.example.demo.repository;

import java.util.*;
import com.example.demo.model.*;

public interface BinRepository {
    Optional<Bin> findById(Long id);
    Optional<Bin> findByIdentifier(String identifier);
    List<Bin> findAll();
    List<Bin> findByZoneAndActiveTrue(Zone zone);
    Bin save(Bin bin);
}
