package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(
        name = "depreciation_rules",
        uniqueConstraints = @UniqueConstraint(columnNames = "ruleName")
)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class DepreciationRule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Rule name is required")
    @Column(nullable = false, unique = true)
    private String ruleName;

    @NotBlank(message = "Method is required")
    @Column(nullable = false)
    private String method;

    @NotNull(message = "Useful life is required")
    @Min(value = 1, message = "Useful life must be positive")
    @Column(nullable = false)
    private Integer usefulLifeYears;

    @NotNull(message = "Salvage value is required")
    @Min(value = 0, message = "Salvage value cannot be negative")
    @Column(nullable = false)
    private Double salvageValue;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    @OneToMany(
            mappedBy = "depreciationRule",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.LAZY
    )
    @JsonIgnore
    private Set<Asset> assets = new HashSet<>();

    public DepreciationRule() {
    }

    public DepreciationRule(
            String ruleName,
            String method,
            Integer usefulLifeYears,
            Double salvageValue
    ) {
        this.ruleName = ruleName;
        this.method = method;
        this.usefulLifeYears = usefulLifeYears;
        this.salvageValue = salvageValue;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    @PrePersist
    public void prePersist() {
        LocalDateTime now = LocalDateTime.now();
        if (createdAt == null) {
            createdAt = now;
        }
        if (updatedAt == null) {
            updatedAt = now;
        }
    }

    @PreUpdate
    public void preUpdate() {
        updatedAt = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Integer getUsefulLifeYears() {
        return usefulLifeYears;
    }

    public void setUsefulLifeYears(Integer usefulLifeYears) {
        this.usefulLifeYears = usefulLifeYears;
    }

    public Double getSalvageValue() {
        return salvageValue;
    }

    public void setSalvageValue(Double salvageValue) {
        this.salvageValue = salvageValue;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Set<Asset> getAssets() {
        return assets;
    }

    public void setAssets(Set<Asset> assets) {
        this.assets = assets;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DepreciationRule)) return false;
        DepreciationRule that = (DepreciationRule) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
