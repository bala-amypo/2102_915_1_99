package com.example.demo.controller;

import org.springframework.web.bind.annotation.*;

import com.example.demo.entity.AssetLifecycleEvent;
import com.example.demo.service.AssetLifecycleEventService;

@RestController
@RequestMapping("/api/events")
public class AssetLifecycleEventController {

    private final AssetLifecycleEventService eventService;

    public AssetLifecycleEventController(AssetLifecycleEventService eventService) {
        this.eventService = eventService;
    }

    @PostMapping("/{assetId}")
    public AssetLifecycleEvent logEvent(
            @PathVariable Long assetId,
            @RequestBody AssetLifecycleEvent event) {

        return eventService.logEvent(assetId, event);
    }
}
