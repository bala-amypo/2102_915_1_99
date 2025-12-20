package com.example.demo.controller;

import com.example.demo.entity.Asset;
import com.example.demo.entity.AssetLifecycleEvent;
import com.example.demo.repository.AssetLifecycleEventRepository;
import com.example.demo.repository.AssetRepository;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/events")
public class AssetLifecycleEventController {

    private final AssetLifecycleEventRepository repository;
    private final AssetRepository assetRepository;

    public AssetLifecycleEventController(AssetLifecycleEventRepository repository,
                                         AssetRepository assetRepository) {
        this.repository = repository;
        this.assetRepository = assetRepository;
    }

    @PostMapping("/{assetId}")
    public AssetLifecycleEvent log(@PathVariable Long assetId,
                                   @RequestBody AssetLifecycleEvent event) {

        Asset asset = assetRepository.findById(assetId).orElseThrow();
        event.setAsset(asset);
        event.setLoggedAt(LocalDateTime.now());

        return repository.save(event);
    }

    @GetMapping("/asset/{assetId}")
    public List<AssetLifecycleEvent> getEvents(@PathVariable Long assetId) {
        return repository.findByAssetIdOrderByEventDateDesc(assetId);
    }
}
