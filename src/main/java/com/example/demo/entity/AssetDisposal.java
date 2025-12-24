// src/main/java/com/example/demo/entity/AssetDisposal.java
package com.example.demo.entity;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "asset_disposals")
public class AssetDisposal {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(optional = false)
    private Asset asset;

    @Column(nullable = false)
    private String disposalMethod;

    @Column(nullable = false)
    private Double disposalValue;

    @Column(nullable = false)
    private LocalDate disposalDate;

    @ManyToOne
    private User approvedBy;

    private LocalDateTime createdAt;

    public AssetDisposal() {}

    public AssetDisposal(Asset asset, String method, Double value, LocalDate date, User approvedBy) {
        this.asset = asset;
        this.disposalMethod = method;
        this.disposalValue = value;
        this.disposalDate = date;
        this.approvedBy = approvedBy;
        this.createdAt = LocalDateTime.now();
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Asset getAsset() { return asset; }
    public void setAsset(Asset asset) { this.asset = asset; }
    public String getDisposalMethod() { return disposalMethod; }
    public void setDisposalMethod(String disposalMethod) { this.disposalMethod = disposalMethod; }
    public Double getDisposalValue() { return disposalValue; }
    public void setDisposalValue(Double disposalValue) { this.disposalValue = disposalValue; }
    public LocalDate getDisposalDate() { return disposalDate; }
    public void setDisposalDate(LocalDate disposalDate) { this.disposalDate = disposalDate; }
    public User getApprovedBy() { return approvedBy; }
    public void setApprovedBy(User approvedBy) { this.approvedBy = approvedBy; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}
