package com.example.demo.service.impl;

import com.example.demo.entity.Asset;
import com.example.demo.entity.Vendor;
import com.example.demo.entity.DepreciationRule;
import com.example.demo.repository.AssetRepository;
import com.example.demo.repository.VendorRepository;
import com.example.demo.repository.DepreciationRuleRepository;
import com.example.demo.service.AssetService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AssetServiceImpl implements AssetService {

    @Autowired
    private AssetRepository assetRepository;

    @Autowired
    private VendorRepository vendorRepository;

    @Autowired
    private DepreciationRuleRepository ruleRepository;

    @Override
    public Asset createAsset(Long vendorId, Long ruleId, Asset asset) {

        Vendor vendor = vendorRepository.findById(vendorId)
                .orElseThrow(() -> new RuntimeException("Vendor not found"));

        DepreciationRule rule = ruleRepository.findById(ruleId)
                .orElseThrow(() -> new RuntimeException("Rule not found"));

        if (asset.getPurchaseCost() < 0) {
            throw new RuntimeException("Invalid purchase cost");
        }

        asset.setVendor(vendor);
        asset.setDepreciationRule(rule);

        return assetRepository.save(asset);
    }

    @Override
    public List<Asset> getAllAssets() {
        return assetRepository.findAll();
    }

    @Override
    public Asset getAssetById(Long id) {
        return assetRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Asset not found"));
    }

    @Override
    public List<Asset> getAssetsByStatus(String status) {
        return assetRepository.findByStatus(status);
    }
}
