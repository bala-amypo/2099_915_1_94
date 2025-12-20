package com.example.demo.service;

import com.example.demo.model.UsagePatternModel;

public interface UsagePatternModelService {

    UsagePatternModel generateOrUpdateModel(Long binId);
}
