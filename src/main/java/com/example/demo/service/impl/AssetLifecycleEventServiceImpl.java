package com.example.demo.service.impl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Asset;
import com.example.demo.entity.AssetLifecycleEvent;
import com.example.demo.repository.AssetLifecycleEventRepository;
import com.example.demo.repository.AssetRepository;
import com.example.demo.service.AssetLifecycleEventService;

@Service
public class AssetLifecycleEventServiceImpl implements AssetLifecycleEventService {

    @Autowired
    private AssetLifecycleEventRepository eventRepository;

    @Autowired
    private AssetRepository assetRepository;

    @Override
    public AssetLifecycleEvent logEvent(Long assetId, AssetLifecycleEvent event) {
        Asset asset = assetRepository.findById(assetId).orElse(null);
        event.setAsset(asset);
        event.setLoggedAt(LocalDateTime.now());
        return eventRepository.save(event);
    }

    @Override
    public List<AssetLifecycleEvent> getEventsForAsset(Long assetId) {
        return eventRepository.findByAssetIdOrderByEventDateDesc(assetId);
    }
}
