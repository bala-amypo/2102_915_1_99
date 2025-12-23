package com.example.demo.service.impl;

import com.example.demo.entity.Asset;
import com.example.demo.entity.AssetLifecycleEvent;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.exception.BadRequestException;
import com.example.demo.repository.AssetLifecycleEventRepository;
import com.example.demo.repository.AssetRepository;
import com.example.demo.service.AssetLifecycleEventService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class AssetLifecycleEventServiceImpl implements AssetLifecycleEventService {
    
    private final AssetLifecycleEventRepository eventRepository;
    private final AssetRepository assetRepository;
    
    public AssetLifecycleEventServiceImpl(AssetLifecycleEventRepository eventRepository, AssetRepository assetRepository) {
        this.eventRepository = eventRepository;
        this.assetRepository = assetRepository;
    }
    
    @Override
    public AssetLifecycleEvent logEvent(Long assetId, AssetLifecycleEvent event) {
        Asset asset = assetRepository.findById(assetId)
            .orElseThrow(() -> new ResourceNotFoundException("Asset not found with id " + assetId));
        
        if (event.getEventType() == null || event.getEventType().trim().isEmpty()) {
            throw new BadRequestException("Event type must not be empty");
        }
        
        if (event.getEventDescription() == null || event.getEventDescription().trim().isEmpty()) {
            throw new BadRequestException("Event description must not be empty");
        }
        
        if (event.getEventDate() == null) {
            throw new BadRequestException("Event date is required");
        }
        
        if (event.getEventDate().isAfter(LocalDate.now())) {
            throw new BadRequestException("Event date cannot be in the future");
        }
        
        // Normalize event type for consistency
        event.setEventType(event.getEventType().trim().toUpperCase());
        
        event.setAsset(asset);
        event.setLoggedAt(LocalDateTime.now());
        event.setUpdatedAt(LocalDateTime.now());
        
        return eventRepository.save(event);
    }
    
    @Override
    public List<AssetLifecycleEvent> getEventsForAsset(Long assetId) {
        if (!assetRepository.existsById(assetId)) {
            throw new ResourceNotFoundException("Asset not found with id " + assetId);
        }
        return eventRepository.findByAssetIdOrderByLoggedAtDesc(assetId);
    }
}
