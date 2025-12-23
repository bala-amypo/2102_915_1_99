package com.example.demo.service.impl;

import com.example.demo.entity.Asset;
import com.example.demo.entity.AssetLifecycleEvent;
import com.example.demo.repository.AssetLifecycleEventRepository;
import com.example.demo.repository.AssetRepository;
import com.example.demo.service.AssetLifecycleEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class AssetLifecycleEventServiceImpl implements AssetLifecycleEventService {

    @Autowired
    private AssetLifecycleEventRepository eventRepository;

    @Autowired
    private AssetRepository assetRepository;

    @Override
    public List<AssetLifecycleEvent> getEventsForAsset(Long assetId) {
        return eventRepository.findByAssetId(assetId);
    }

    @Override
    public AssetLifecycleEvent logEvent(Long assetId, AssetLifecycleEvent event) {

        Asset asset = assetRepository.findById(assetId).orElseThrow();

        if (event.getEventDate().isAfter(LocalDate.now())) {
            throw new RuntimeException("Future date not allowed");
        }

        event.setAsset(asset);
        return eventRepository.save(event);
    }
}
