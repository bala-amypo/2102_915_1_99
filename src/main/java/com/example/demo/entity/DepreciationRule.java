package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "depreciation_rules")
public class DepreciationRule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "rule_name", unique = true, nullable = false)
    private String ruleName;

    private String method;

    @Column(name = "useful_life_years")
    private int usefulLifeYears;

    @Column(name = "salvage_value")
    private double salvageValue;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "depreciationRule", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Asset> assets;

    public DepreciationRule() {
    }

    public Long getId() {
        return id;
    }

    public String getRuleName() {
        return ruleName;
    }

    public void setRuleName(String ruleName) {
        this.ruleName = ruleName;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public int getUsefulLifeYears() {
        return usefulLifeYears;
    }

    public void setUsefulLifeYears(int usefulLifeYears) {
        this.usefulLifeYears = usefulLifeYears;
    }

    public double getSalvageValue() {
        return salvageValue;
    }

    public void setSalvageValue(double salvageValue) {
        this.salvageValue = salvageValue;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public List<Asset> getAssets() {
        return assets;
    }

    public void setAssets(List<Asset> assets) {
        this.assets = assets;
    }
}
