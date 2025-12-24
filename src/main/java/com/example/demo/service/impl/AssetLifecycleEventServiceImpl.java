// src/main/java/com/example/demo/service/impl/AssetLifecycleEventServiceImpl.java
package com.example.demo.service.impl;

import com.example.demo.entity.Asset;
import com.example.demo.entity.AssetLifecycleEvent;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.AssetLifecycleEventRepository;
import com.example.demo.repository.AssetRepository;
import com.example.demo.service.AssetLifecycleEventService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class AssetLifecycleEventServiceImpl implements AssetLifecycleEventService {

    private final AssetLifecycleEventRepository eventRepo;
    private final AssetRepository assetRepo;

    public AssetLifecycleEventServiceImpl(AssetLifecycleEventRepository eventRepo,
                                          AssetRepository assetRepo) {
        this.eventRepo = eventRepo;
        this.assetRepo = assetRepo;
    }

    @Override
    public AssetLifecycleEvent logEvent(Long assetId, AssetLifecycleEvent event) {
        Asset asset = assetRepo.findById(assetId)
            .orElseThrow(() -> new ResourceNotFoundException("Asset not found"));

        if (event.getEventType() == null || event.getEventType().isBlank()) {
            throw new IllegalArgumentException("Event type required");
        }
        if (event.getEventDescription() == null || event.getEventDescription().isBlank()) {
            throw new IllegalArgumentException("Event description required");
        }
        if (event.getEventDate() == null || event.getEventDate().isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("Event date invalid");
        }

        event.setAsset(asset);
        event.setLoggedAt(LocalDateTime.now());
        return eventRepo.save(event);
    }

    @Override
    public List<AssetLifecycleEvent> getEventsForAsset(Long assetId) {
        assetRepo.findById(assetId).orElseThrow(() -> new ResourceNotFoundException("Asset not found"));
        // Align with repository method signature using underscore between asset and id
        return eventRepo.findByAsset_IdOrderByEventDateDesc(assetId);
    }
}
