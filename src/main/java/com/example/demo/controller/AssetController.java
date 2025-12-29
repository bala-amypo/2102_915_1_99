
package com.example.demo.controller;

import com.example.demo.entity.Asset;
import com.example.demo.service.AssetService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/assets")
public class AssetController {
    private final AssetService assetService;

    public AssetController(AssetService assetService) {
        this.assetService = assetService;
    }

    @PostMapping("/{vendorId}/{ruleId}")
    public ResponseEntity<Asset> create(@PathVariable Long vendorId,
                                        @PathVariable Long ruleId,
                                        @RequestBody Asset asset) {
        Asset saved = assetService.createAsset(vendorId, ruleId, asset);
        return ResponseEntity.ok(saved);
    }

    @GetMapping
    public ResponseEntity<List<Asset>> all() {
        return ResponseEntity.ok(assetService.getAllAssets());
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<Asset>> byStatus(@PathVariable String status) {
        return ResponseEntity.ok(assetService.getAssetsByStatus(status));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Asset> one(@PathVariable Long id) {
        return ResponseEntity.ok(assetService.getAsset(id));
    }
}
