package com.example.demo.service.impl;

import com.example.demo.entity.Asset;
import com.example.demo.entity.DepreciationRule;
import com.example.demo.entity.Vendor;
import com.example.demo.repository.AssetRepository;
import com.example.demo.repository.DepreciationRuleRepository;
import com.example.demo.repository.VendorRepository;
import com.example.demo.service.AssetService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AssetServiceImpl implements AssetService {

    private final AssetRepository assetRepository;
    private final VendorRepository vendorRepository;
    private final DepreciationRuleRepository depreciationRuleRepository;

    public AssetServiceImpl(
            AssetRepository assetRepository,
            VendorRepository vendorRepository,
            DepreciationRuleRepository depreciationRuleRepository
    ) {
        this.assetRepository = assetRepository;
        this.vendorRepository = vendorRepository;
        this.depreciationRuleRepository = depreciationRuleRepository;
    }

    @Override
    public Asset createAsset(Long vendorId, Long ruleId, Asset asset) {
        // Ensure vendor exists
        Vendor vendor = vendorRepository.findById(vendorId)
                .orElseThrow(() -> new IllegalArgumentException("Vendor not found"));

        // Ensure depreciation rule exists
        DepreciationRule rule = depreciationRuleRepository.findById(ruleId)
                .orElseThrow(() -> new IllegalArgumentException("Depreciation rule not found"));

        // Validate purchase cost
        if (asset.getPurchaseCost() <= 0) {
            throw new IllegalArgumentException("Purchase cost must be greater than zero");
        }

        // Ensure asset tag is unique
        if (assetRepository.existsByAssetTag(asset.getAssetTag())) {
            throw new IllegalArgumentException("Asset tag already exists");
        }

        // Link vendor and rule
        asset.setVendor(vendor);
        asset.setDepreciationRule(rule);

        // Default status
        if (asset.getStatus() == null || asset.getStatus().isBlank()) {
            asset.setStatus("ACTIVE");
        }

        // Audit fields handled by @PrePersist/@PreUpdate in entity
        return assetRepository.save(asset);
    }

    @Override
    public List<Asset> getAllAssets() {
        return assetRepository.findAll();
    }

    @Override
    public Asset getAsset(Long id) {
        return assetRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Asset not found"));
    }

    @Override
    public List<Asset> getAssetsByStatus(String status) {
        // Normalize case
        return assetRepository.findByStatus(status.toUpperCase());
    }
}
