package com.example.demo.entity;

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

    @Column(unique = true)
    private String assetTag;

    private String assetName;
    private LocalDate purchaseDate;
    private Double purchaseCost;
    private String status;
    private LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(name = "vendor_id")
    private Vendor vendor;

    @ManyToOne
    @JoinColumn(name = "rule_id")
    private DepreciationRule depreciationRule;

    @OneToMany(mappedBy = "asset")
    private List<AssetLifecycleEvent> events;

    @OneToOne(mappedBy = "asset")
    private AssetDisposal disposal;

    public Asset() {
        this.status = "ACTIVE";
        this.createdAt = LocalDateTime.now();
    }

    public Asset(String assetTag, String assetName, LocalDate purchaseDate, Double purchaseCost) {
        this.assetTag = assetTag;
        this.assetName = assetName;
        this.purchaseDate = purchaseDate;
        this.purchaseCost = purchaseCost;
        this.status = "ACTIVE";
        this.createdAt = LocalDateTime.now();
    }

    public Long getId() { return id; }
    public String getAssetTag() { return assetTag; }
    public String getStatus() { return status; }

    public void setStatus(String status) { this.status = status; }
}
