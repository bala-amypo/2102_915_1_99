package com.example.demo.service.impl;

import com.example.demo.entity.DepreciationRule;
import com.example.demo.exception.BadRequestException;
import com.example.demo.repository.DepreciationRuleRepository;
import com.example.demo.service.DepreciationRuleService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

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
            throw new BadRequestException("Useful life years must be positive");
        }
        
        // Validate salvage value
        if (rule.getSalvageValue() < 0) {
            throw new BadRequestException("Salvage value cannot be negative");
        }
        
        // Validate method (case-insensitive)
        String method = rule.getMethod().toUpperCase();
        if (!method.equals("STRAIGHT_LINE") && !method.equals("DECLINING_BALANCE")) {
            throw new BadRequestException("Method must be STRAIGHT_LINE or DECLINING_BALANCE");
        }
        rule.setMethod(method);

        // Check for duplicate rule name (case-insensitive)
        if (depreciationRuleRepository.existsByRuleNameIgnoreCase(rule.getRuleName())) {
            throw new BadRequestException("Depreciation rule with name '" + rule.getRuleName() + "' already exists");
        }
        
        // Set audit fields
        rule.setCreatedAt(LocalDateTime.now());
        rule.setUpdatedAt(LocalDateTime.now());
        
        return depreciationRuleRepository.save(rule);
    }
}
