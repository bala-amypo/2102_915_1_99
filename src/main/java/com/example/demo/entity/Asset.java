package com.example.demo.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "assets", uniqueConstraints = {
        @UniqueConstraint(columnNames = "assetTag")
})
public class Asset {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String assetTag;

    private String assetName;
    private LocalDate purchaseDate;
    private double purchaseCost;

    private String status = "ACTIVE";

    @ManyToOne(optional = false)
    private Vendor vendor;

    @ManyToOne(optional = false)
    private DepreciationRule depreciationRule;

    public Asset() {}

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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
