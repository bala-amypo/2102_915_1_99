// src/main/java/com/example/demo/service/impl/DepreciationRuleServiceImpl.java
package com.example.demo.service.impl;

import com.example.demo.entity.DepreciationRule;
import com.example.demo.service.DepreciationRuleService;
import com.example.demo.repository.DepreciationRuleRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class DepreciationRuleServiceImpl implements DepreciationRuleService {

    private final DepreciationRuleRepository ruleRepo;

    public DepreciationRuleServiceImpl(DepreciationRuleRepository ruleRepo) {
        this.ruleRepo = ruleRepo;
    }

    @Override
    public DepreciationRule createRule(DepreciationRule rule) {
        if (rule.getUsefulLifeYears() == null || rule.getUsefulLifeYears() <= 0) {
            throw new IllegalArgumentException("Invalid useful life");
        }
        if (rule.getSalvageValue() == null || rule.getSalvageValue() < 0) {
            throw new IllegalArgumentException("Invalid salvage value");
        }
        if (!"STRAIGHT_LINE".equals(rule.getMethod()) && !"DECLINING_BALANCE".equals(rule.getMethod())) {
            throw new IllegalArgumentException("Invalid method");
        }
        rule.setCreatedAt(LocalDateTime.now());
        return ruleRepo.save(rule);
    }

    @Override
    public List<DepreciationRule> getAllRules() {
        return ruleRepo.findAll();
    }
}
