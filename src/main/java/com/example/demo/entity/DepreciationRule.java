package com.example.demo.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(
        name = "depreciation_rules",
        uniqueConstraints = @UniqueConstraint(columnNames = "ruleName")
)
public class DepreciationRule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String ruleName;

    private String method;

    private Integer usefulLifeYears;

    private Double salvageValue;

    private LocalDateTime createdAt = LocalDateTime.now();

    @OneToMany(mappedBy = "depreciationRule")
    private List<Asset> assets;

    public DepreciationRule() {
    }

    // getters & setters

    public Long getId() {
        return id;
    }

    public String getRuleName() {
        return ruleName;
    }

    public void setRuleName(String ruleName) {
        this.ruleName = ruleName;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public void setUsefulLifeYears(Integer usefulLifeYears) {
        this.usefulLifeYears = usefulLifeYears;
    }

    public void setSalvageValue(Double salvageValue) {
        this.salvageValue = salvageValue;
    }
}
