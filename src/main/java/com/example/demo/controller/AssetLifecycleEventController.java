package com.example.demo.controller;

import com.example.demo.entity.AssetLifecycleEvent;
import com.example.demo.service.AssetLifecycleEventService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/events")
public class AssetLifecycleEventController {
    
    private final AssetLifecycleEventService eventService;
    
    public AssetLifecycleEventController(AssetLifecycleEventService eventService) {
        this.eventService = eventService;
    }
    
    @PostMapping("/{assetId}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    public ResponseEntity<?> logEvent(@PathVariable Long assetId, 
                                      @RequestBody AssetLifecycleEvent event) {
        try {
            AssetLifecycleEvent logged = eventService.logEvent(assetId, event);
            return ResponseEntity.status(HttpStatus.CREATED).body(logged);
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }
    }
    
    @GetMapping("/asset/{assetId}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    public ResponseEntity<List<AssetLifecycleEvent>> getEventsForAsset(@PathVariable Long assetId) {
        List<AssetLifecycleEvent> events = eventService.getEventsForAsset(assetId);
        return ResponseEntity.ok(events);
    }
}
