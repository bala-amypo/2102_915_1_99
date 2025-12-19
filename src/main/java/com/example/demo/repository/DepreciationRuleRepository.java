package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.DepreciationRule;

public interface DepreciationRuleRepository extends JpaRepository<DepreciationRule, Long> {

    Optional<DepreciationRule> findByRuleName(String ruleName);
}
