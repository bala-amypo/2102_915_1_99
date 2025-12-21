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

    private LocalDateTime loggedAt = LocalDateTime.now();

    @ManyToOne
    @JoinColumn(name = "asset_id")
    private Asset asset;

    public AssetLifecycleEvent() {
    }

    // getters & setters

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public void setEventDescription(String eventDescription) {
        this.eventDescription = eventDescription;
    }

    public void setEventDate(LocalDate eventDate) {
        this.eventDate = eventDate;
    }

    public void setAsset(Asset asset) {
        this.asset = asset;
    }
}
