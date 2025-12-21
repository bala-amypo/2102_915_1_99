package com.example.demo.service.impl;

import com.example.demo.entity.DepreciationRule;
import com.example.demo.repository.DepreciationRuleRepository;
import com.example.demo.service.DepreciationRuleService;
import org.springframework.stereotype.Service;

@Service
public class DepreciationRuleServiceImpl implements DepreciationRuleService {

    private final DepreciationRuleRepository ruleRepository;

    public DepreciationRuleServiceImpl(DepreciationRuleRepository ruleRepository) {
        this.ruleRepository = ruleRepository;
    }

    @Override
    public DepreciationRule createRule(DepreciationRule rule) {

        if (rule.getUsefulLifeYears() == null || rule.getUsefulLifeYears() <= 0) {
            throw new IllegalArgumentException("Invalid useful life");
        }

        if (rule.getSalvageValue() < 0) {
            throw new IllegalArgumentException("Invalid salvage value");
        }

        if (!rule.getMethod().equals("STRAIGHT_LINE")
                && !rule.getMethod().equals("DECLINING_BALANCE")) {
            throw new IllegalArgumentException("Invalid depreciation method");
        }

        return ruleRepository.save(rule);
    }
}
