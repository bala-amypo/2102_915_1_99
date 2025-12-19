package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.demo.entity.Asset;
import com.example.demo.service.AssetService;

@RestController
@RequestMapping("/api/assets")
public class AssetController {

    @Autowired
    private AssetService assetService;

    @PostMapping("/{vendorId}/{ruleId}")
    public Asset createAsset(
            @PathVariable Long vendorId,
            @PathVariable Long ruleId,
            @RequestBody Asset asset) {
        return assetService.createAsset(vendorId, ruleId, asset);
    }

    @GetMapping
    public List<Asset> getAllAssets() {
        return assetService.getAllAssets();
    }

    @GetMapping("/{id}")
    public Asset getAsset(@PathVariable Long id) {
        return assetService.getAssetById(id);
    }

    @GetMapping("/status/{status}")
    public List<Asset> getByStatus(@PathVariable String status) {
        return assetService.getAssetsByStatus(status);
    }
}
