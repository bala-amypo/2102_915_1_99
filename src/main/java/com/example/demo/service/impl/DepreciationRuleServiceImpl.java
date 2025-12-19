package com.example.demo.service.impl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.DepreciationRule;
import com.example.demo.repository.DepreciationRuleRepository;
import com.example.demo.service.DepreciationRuleService;

@Service
public class DepreciationRuleServiceImpl implements DepreciationRuleService {

    @Autowired
    private DepreciationRuleRepository ruleRepository;

    @Override
    public DepreciationRule createRule(DepreciationRule rule) {
        rule.setCreatedAt(LocalDateTime.now());
        return ruleRepository.save(rule);
    }

    @Override
    public List<DepreciationRule> getAllRules() {
        return ruleRepository.findAll();
    }

    @Override
    public DepreciationRule getRuleById(Long id) {
        return ruleRepository.findById(id).orElse(null);
    }
}
