package com.example.demo.repository;

import com.example.demo.entity.Bin;
import com.example.demo.entity.Zone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface BinRepository extends JpaRepository<Bin, Long> {
    List<Bin> findByZone(Zone zone);
}
