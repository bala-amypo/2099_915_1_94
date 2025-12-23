package com.example.demo.service;

import java.util.List;

import com.example.demo.model.UsagePatternModel;

public interface UsagePatternModelService {

    UsagePatternModel create(UsagePatternModel model);

    UsagePatternModel update(Long id, UsagePatternModel model);

    List<UsagePatternModel> getAll();

    UsagePatternModel getByBinId(Long binId);
}
