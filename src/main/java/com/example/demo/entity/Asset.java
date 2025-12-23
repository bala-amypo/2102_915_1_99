package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "assets")
public class Asset {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "asset_tag", unique = true, nullable = false)
    private String assetTag;

    @Column(name = "asset_name", nullable = false)
    private String assetName;

    @Column(name = "purchase_date", nullable = false)
    private LocalDate purchaseDate;

    @Column(name = "purchase_cost", nullable = false)
    private double purchaseCost;

    @Column(nullable = false)
    private String status;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(name = "vendor_id")
    @JsonBackReference
    private Vendor vendor;

    @ManyToOne
    @JoinColumn(name = "depreciation_rule_id")
    @JsonBackReference
    private DepreciationRule depreciationRule;

    @OneToMany(mappedBy = "asset", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<AssetLifecycleEvent> lifecycleEvents;

    public Asset() {
    }

    public Asset(String assetTag, String assetName, LocalDate purchaseDate,
                 double purchaseCost, String status) {
        this.assetTag = assetTag;
        this.assetName = assetName;
        this.purchaseDate = purchaseDate;
        this.purchaseCost = purchaseCost;
        this.status = status;
        this.createdAt = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public String getAssetTag() {
        return assetTag;
    }

    public void setAssetTag(String assetTag) {
        this.assetTag = assetTag;
    }

    public String getAssetName() {
        return assetName;
    }

    public void setAssetName(String assetName) {
        this.assetName = assetName;
    }

    public LocalDate getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(LocalDate purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public double getPurchaseCost() {
        return purchaseCost;
    }

    public void setPurchaseCost(double purchaseCost) {
        this.purchaseCost = purchaseCost;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public Vendor getVendor() {
        return vendor;
    }

    public void setVendor(Vendor vendor) {
        this.vendor = vendor;
    }

    public DepreciationRule getDepreciationRule() {
        return depreciationRule;
    }

    public void setDepreciationRule(DepreciationRule depreciationRule) {
        this.depreciationRule = depreciationRule;
    }

    public List<AssetLifecycleEvent> getLifecycleEvents() {
        return lifecycleEvents;
    }

    public void setLifecycleEvents(List<AssetLifecycleEvent> lifecycleEvents) {
        this.lifecycleEvents = lifecycleEvents;
    }
}
