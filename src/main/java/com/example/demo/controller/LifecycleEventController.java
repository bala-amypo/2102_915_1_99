package com.example.demo.controller;

import com.example.demo.entity.AssetLifecycleEvent;
import com.example.demo.service.AssetLifecycleEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/events")
public class LifecycleEventController {

    @Autowired
    private AssetLifecycleEventService service;

    @PostMapping("/{assetId}")
    public ResponseEntity<List<AssetLifecycleEvent>> logEvent(
            @PathVariable Long assetId,
            @RequestBody AssetLifecycleEvent event) {

        AssetLifecycleEvent saved = service.logEvent(assetId, event);
        return ResponseEntity.ok(List.of(saved));
    }

    @GetMapping("/{assetId}")
    public ResponseEntity<List<AssetLifecycleEvent>> getEvents(
            @PathVariable Long assetId) {

        return ResponseEntity.ok(service.getEventsForAsset(assetId));
    }
}
