package com.example.demo.service;

import com.example.demo.entity.AssetLifecycleEvent;

import java.util.List;

public interface AssetLifecycleEventService {

    List<AssetLifecycleEvent> getEventsForAsset(Long assetId);

    AssetLifecycleEvent logEvent(Long assetId, AssetLifecycleEvent event);
}
    