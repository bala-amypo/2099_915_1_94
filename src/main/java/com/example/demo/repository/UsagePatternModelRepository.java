package com.example.demo.repository;

import java.util.*;
import com.example.demo.model.*;

public interface UsagePatternModelRepository {
    Optional<UsagePatternModel> findById(Long id);
    Optional<UsagePatternModel> findTop1ByBinOrderByLastUpdatedDesc(Bin bin);
    UsagePatternModel save(UsagePatternModel model);
}
