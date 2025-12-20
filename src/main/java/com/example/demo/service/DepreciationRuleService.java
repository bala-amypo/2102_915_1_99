package com.example.demo.service;

import com.example.demo.entity.DepreciationRule;

import java.util.List;

public interface DepreciationRuleService {
    DepreciationRule save(DepreciationRule rule);
    List<DepreciationRule> findAll();
}
