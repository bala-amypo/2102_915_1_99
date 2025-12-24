package com.example.demo.service.impl;

import com.example.demo.entity.DepreciationRule;
import com.example.demo.repository.DepreciationRuleRepository;
import com.example.demo.service.DepreciationRuleService;
import org.springframework.stereotype.Service;

@Service
public class DepreciationRuleServiceImpl implements DepreciationRuleService {
    
    private final DepreciationRuleRepository depreciationRuleRepository;
    
    public DepreciationRuleServiceImpl(DepreciationRuleRepository depreciationRuleRepository) {
        this.depreciationRuleRepository = depreciationRuleRepository;
    }
    
    @Override
    public DepreciationRule createRule(DepreciationRule rule) {
        // Validate useful life
        if (rule.getUsefulLifeYears() <= 0) {
            throw new IllegalArgumentException("Useful life years must be positive");
        }
        
        // Validate salvage value
        if (rule.getSalvageValue() < 0) {
            throw new IllegalArgumentException("Salvage value cannot be negative");
        }
        
        // Validate method (case-insensitive)
        String method = rule.getMethod().toUpperCase();
        if (!method.equals("STRAIGHT_LINE") && !method.equals("DECLINING_BALANCE")) {
            throw new IllegalArgumentException("Method must be STRAIGHT_LINE or DECLINING_BALANCE");
        }
        rule.setMethod(method);

        // Check for duplicate rule name
        if (depreciationRuleRepository.existsByRuleName(rule.getRuleName())) {
            throw new IllegalArgumentException("Depreciation rule with name '" + rule.getRuleName() + "' already exists");
        }
        
        // Audit fields handled by @PrePersist/@PreUpdate in entity
        return depreciationRuleRepository.save(rule);
    }
}
