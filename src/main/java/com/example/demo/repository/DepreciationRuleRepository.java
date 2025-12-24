package com.example.demo.repository;

import com.example.demo.entity.DepreciationRule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface DepreciationRuleRepository extends JpaRepository<DepreciationRule, Long> {

    Optional<DepreciationRule> findByRuleNameIgnoreCase(String ruleName);

    boolean existsByRuleNameIgnoreCase(String ruleName);

    // Add this to satisfy tests that expect findByRuleName(String)
    Optional<DepreciationRule> findByRuleName(String ruleName);
}
