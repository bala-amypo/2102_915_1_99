package com.example.demo.service.impl;

import com.example.demo.entity.AssetLifecycleEvent;
import com.example.demo.repository.AssetLifecycleEventRepository;
import com.example.demo.service.AssetLifecycleEventService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AssetLifecycleEventServiceImpl implements AssetLifecycleEventService {

    private final AssetLifecycleEventRepository repository;

    public AssetLifecycleEventServiceImpl(AssetLifecycleEventRepository repository) {
        this.repository = repository;
    }

    @Override
    public AssetLifecycleEvent save(AssetLifecycleEvent event) {
        return repository.save(event);
    }

    @Override
    public List<AssetLifecycleEvent> findByAsset(Long assetId) {
        return repository.findByAssetIdOrderByEventDateDesc(assetId);
    }
}
