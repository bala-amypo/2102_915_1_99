// src/main/java/com/example/demo/entity/Asset.java
package com.example.demo.entity;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "assets", uniqueConstraints = @UniqueConstraint(columnNames = "assetTag"))
public class Asset {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String assetTag;

    @Column(nullable = false)
    private String assetName;

    @ManyToOne(optional = false)
    private Vendor vendor;

    private LocalDate purchaseDate;

    @Column(nullable = false)
    private Double purchaseCost;

    @ManyToOne(optional = false)
    private DepreciationRule depreciationRule;

    @Column(nullable = false)
    private String status = "ACTIVE"; // default

    private LocalDateTime createdAt;

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
    }

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
}
