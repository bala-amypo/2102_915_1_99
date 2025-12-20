package com.example.demo.entity;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "asset_disposals")
public class AssetDisposal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String disposalMethod;
    private Double disposalValue;
    private LocalDate disposalDate;
    private LocalDateTime createdAt;

    @OneToOne
    @JoinColumn(name = "asset_id")
    private Asset asset;

    @ManyToOne
    @JoinColumn(name = "approved_by")
    private User approvedBy;

    public AssetDisposal() {
        this.createdAt = LocalDateTime.now();
    }

    public Long getId() { return id; }
    public Asset getAsset() { return asset; }

    public void setAsset(Asset asset) { this.asset = asset; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
    public void setApprovedBy(User approvedBy) { this.approvedBy = approvedBy; }
}
