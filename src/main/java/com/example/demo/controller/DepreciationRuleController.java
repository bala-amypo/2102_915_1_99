// DepreciationRuleController.java
package com.example.demo.controller;

import com.example.demo.entity.DepreciationRule;
import com.example.demo.service.DepreciationRuleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rules")
public class DepreciationRuleController {
    
    private final DepreciationRuleService ruleService;
    private final com.example.demo.repository.DepreciationRuleRepository ruleRepository;
    
    public DepreciationRuleController(DepreciationRuleService ruleService,
                                     com.example.demo.repository.DepreciationRuleRepository ruleRepository) {
        this.ruleService = ruleService;
        this.ruleRepository = ruleRepository;
    }
    
    @PostMapping
    public ResponseEntity<DepreciationRule> createRule(@RequestBody DepreciationRule rule) {
        DepreciationRule created = ruleService.createRule(rule);
        return ResponseEntity.ok(created);
    }
    
    @GetMapping
    public ResponseEntity<List<DepreciationRule>> getAllRules() {
        List<DepreciationRule> rules = ruleRepository.findAll();
        return ResponseEntity.ok(rules);
    }
}