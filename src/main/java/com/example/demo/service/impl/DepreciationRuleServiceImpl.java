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
        if (rule.getUsefulLifeYears() <= 0) {
            throw new BadRequestException("Useful life years must be greater than 0");
        }
        
        if (rule.getSalvageValue() < 0) {
            throw new BadRequestException("Salvage value must be greater than or equal to 0");
        }
        
        if (!rule.getMethod().equals("STRAIGHT_LINE") && !rule.getMethod().equals("DECLINING_BALANCE")) {
            throw new BadRequestException("Method must be STRAIGHT_LINE or DECLINING_BALANCE");
        }
        
        rule.setCreatedAt(LocalDateTime.now());
        return depreciationRuleRepository.save(rule);
    }
}