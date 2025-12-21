package com.example.demo.repository;

import java.util.*;
import com.example.demo.model.*;

public interface FillLevelRecordRepository {
    Optional<FillLevelRecord> findById(Long id);
    List<FillLevelRecord> findByBinOrderByRecordedAtDesc(Bin bin);
    Optional<FillLevelRecord> findTop1ByBinOrderByRecordedAtDesc(Bin bin);
    List<FillLevelRecord> findByBinAndRecordedAtBetween(
        Bin bin, java.time.LocalDateTime s, java.time.LocalDateTime e);
    FillLevelRecord save(FillLevelRecord record);
}
