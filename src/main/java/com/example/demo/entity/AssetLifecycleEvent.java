package com.example.demo.entity;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "asset_lifecycle_events")
public class AssetLifecycleEvent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String eventType;
    private String eventDescription;
    private LocalDate eventDate;
    private LocalDateTime loggedAt;

    @ManyToOne
    @JoinColumn(name = "asset_id")
    private Asset asset;

    public AssetLifecycleEvent() {
        this.loggedAt = LocalDateTime.now();
    }

    public Long getId() { return id; }

    public void setAsset(Asset asset) { this.asset = asset; }
    public void setLoggedAt(LocalDateTime loggedAt) { this.loggedAt = loggedAt; }
}
