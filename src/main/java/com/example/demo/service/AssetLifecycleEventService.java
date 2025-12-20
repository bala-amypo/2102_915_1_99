package com.example.demo.service;

import com.example.demo.entity.AssetLifecycleEvent;

import java.util.List;

public interface AssetLifecycleEventService {
    AssetLifecycleEvent save(AssetLifecycleEvent event);
    List<AssetLifecycleEvent> findByAsset(Long assetId);
}
