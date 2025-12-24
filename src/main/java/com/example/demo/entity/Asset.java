package com.example.demo.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "assets", uniqueConstraints = @UniqueConstraint(columnNames = "assetTag"))
public class Asset {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Asset tag is required")
    @Column(nullable = false, unique = true)
    private String assetTag;

    @NotBlank(message = "Asset name is required")
    @Column(nullable = false)
    private String assetName;

    @ManyToOne(optional = false)
    @JoinColumn(name = "vendor_id", nullable = false)
    private Vendor vendor;

    private LocalDate purchaseDate;

    @NotNull(message = "Purchase cost is required")
    @Column(nullable = false)
    private Double purchaseCost;

    @ManyToOne(optional = false)
    @JoinColumn(name = "depreciation_rule_id", nullable = false)
    private DepreciationRule depreciationRule;

    @Column(nullable = false)
    private String status = "ACTIVE";

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    @OneToMany(mappedBy = "asset", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<AssetLifecycleEvent> lifecycleEvents = new HashSet<>();

    public Asset() {}

    public Asset(String assetTag, String assetName, Vendor vendor,
                 LocalDate purchaseDate, Double purchaseCost, DepreciationRule rule) {
        this.assetTag = assetTag;
        this.assetName = assetName;
        this.vendor = vendor;
        this.purchaseDate = purchaseDate;
        this.purchaseCost = purchaseCost;
        this.depreciationRule = rule;
        this.status = "ACTIVE";
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

    // Getters and setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getAssetTag() { return assetTag; }
    public void setAssetTag(String assetTag) { this.assetTag = assetTag; }

    public String getAssetName() { return assetName; }
    public void setAssetName(String assetName) { this.assetName = assetName; }

    public Vendor getVendor() { return vendor; }
    public void setVendor(Vendor vendor) { this.vendor = vendor; }

    public LocalDate getPurchaseDate() { return purchaseDate; }
    public void setPurchaseDate(LocalDate purchaseDate) { this.purchaseDate = purchaseDate; }

    public Double getPurchaseCost() { return purchaseCost; }
    public void setPurchaseCost(Double purchaseCost) { this.purchaseCost = purchaseCost; }

    public DepreciationRule getDepreciationRule() { return depreciationRule; }
    public void setDepreciationRule(DepreciationRule depreciationRule) { this.depreciationRule = depreciationRule; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }

    public Set<AssetLifecycleEvent> getLifecycleEvents() { return lifecycleEvents; }
    public void setLifecycleEvents(Set<AssetLifecycleEvent> lifecycleEvents) { this.lifecycleEvents = lifecycleEvents; }

    // equals and hashCode based on id
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Asset)) return false;
        Asset other = (Asset) o;
        return Objects.equals(id, other.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
