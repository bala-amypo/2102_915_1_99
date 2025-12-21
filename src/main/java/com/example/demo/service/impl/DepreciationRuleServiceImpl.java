// DepreciationRuleServiceImpl.java
package com.example.demo.service.impl;

import com.example.demo.entity.DepreciationRule;
import com.example.demo.repository.DepreciationRuleRepository;
import com.example.demo.service.DepreciationRuleService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@Service
@Transactional
public class DepreciationRuleServiceImpl implements DepreciationRuleService {
    
    private final DepreciationRuleRepository ruleRepository;
    private static final List<String> VALID_METHODS = 
        Arrays.asList("STRAIGHT_LINE", "DECLINING_BALANCE");
    
    public DepreciationRuleServiceImpl(DepreciationRuleRepository ruleRepository) {
        this.ruleRepository = ruleRepository;
    }
    
    @Override
    public DepreciationRule createRule(DepreciationRule rule) {
        if (rule.getUsefulLifeYears() <= 0) {
            throw new IllegalArgumentException("Useful life years must be greater than 0");
        }
        
        if (rule.getSalvageValue() < 0) {
            throw new IllegalArgumentException("Salvage value cannot be negative");
        }
        
        if (!VALID_METHODS.contains(rule.getMethod())) {
            throw new IllegalArgumentException("Method must be STRAIGHT_LINE or DECLINING_BALANCE");
        }
        
        rule.setCreatedAt(LocalDateTime.now());
        return ruleRepository.save(rule);
    }
}