// src/main/java/com/example/demo/controller/DepreciationRuleController.java
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

    public DepreciationRuleController(DepreciationRuleService ruleService) {
        this.ruleService = ruleService;
    }

    @PostMapping
    public ResponseEntity<DepreciationRule> create(@RequestBody DepreciationRule rule) {
        return ResponseEntity.ok(ruleService.createRule(rule));
    }

    @GetMapping
    public ResponseEntity<List<DepreciationRule>> all() {
        return ResponseEntity.ok(ruleService.getAllRules());
    }
}
