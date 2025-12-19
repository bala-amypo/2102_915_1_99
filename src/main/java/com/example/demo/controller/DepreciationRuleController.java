package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.demo.entity.DepreciationRule;
import com.example.demo.service.DepreciationRuleService;

@RestController
@RequestMapping("/api/rules")
public class DepreciationRuleController {

    @Autowired
    private DepreciationRuleService ruleService;

    @PostMapping
    public DepreciationRule createRule(@RequestBody DepreciationRule rule) {
        return ruleService.createRule(rule);
    }

    @GetMapping
    public List<DepreciationRule> getAllRules() {
        return ruleService.getAllRules();
    }

    @GetMapping("/{id}")
    public DepreciationRule getRule(@PathVariable Long id) {
        return ruleService.getRuleById(id);
    }
}
