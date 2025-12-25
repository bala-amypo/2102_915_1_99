package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "asset_lifecycle_events")
public class AssetLifecycleEvent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "asset_id", nullable = false)
    @JsonIgnoreProperties({"lifecycleEvents", "vendor", "depreciationRule"})
    private Asset asset;

    @NotBlank(message = "Event type is required")
    @Column(nullable = false)
    private String eventType;

    @NotBlank(message = "Event description is required")
    @Column(nullable = false)
    private String eventDescription;

    @NotNull(message = "Event date is required")
    @Column(nullable = false)
    private LocalDate eventDate;

    @Column(name = "logged_at", nullable = false)
    private LocalDateTime loggedAt;

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    public AssetLifecycleEvent() {
    }

    public AssetLifecycleEvent(
            Asset asset,
            String eventType,
            String eventDescription,
            LocalDate eventDate
    ) {
        this.asset = asset;
        this.eventType = eventType;
        this.eventDescription = eventDescription;
        this.eventDate = eventDate;
        this.loggedAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    @PrePersist
    public void prePersist() {
        LocalDateTime now = LocalDateTime.now();
        if (loggedAt == null) {
            loggedAt = now;
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

    public Asset getAsset() {
        return asset;
    }

    public void setAsset(Asset asset) {
        this.asset = asset;
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public String getEventDescription() {
        return eventDescription;
    }

    public void setEventDescription(String eventDescription) {
        this.eventDescription = eventDescription;
    }

    public LocalDate getEventDate() {
        return eventDate;
    }

    public void setEventDate(LocalDate eventDate) {
        this.eventDate = eventDate;
    }

    public LocalDateTime getLoggedAt() {
        return loggedAt;
    }

    public void setLoggedAt(LocalDateTime loggedAt) {
        this.loggedAt = loggedAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AssetLifecycleEvent)) return false;
        AssetLifecycleEvent that = (AssetLifecycleEvent) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
