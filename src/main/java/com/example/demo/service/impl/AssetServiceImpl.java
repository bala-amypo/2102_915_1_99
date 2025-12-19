package com.example.demo.service.impl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Asset;
import com.example.demo.entity.DepreciationRule;
import com.example.demo.entity.Vendor;
import com.example.demo.repository.AssetRepository;
import com.example.demo.repository.DepreciationRuleRepository;
import com.example.demo.repository.VendorRepository;
import com.example.demo.service.AssetService;

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
        Vendor vendor = vendorRepository.findById(vendorId).orElse(null);
        DepreciationRule rule = ruleRepository.findById(ruleId).orElse(null);

        asset.setVendor(vendor);
        asset.setDepreciationRule(rule);
        asset.setStatus("ACTIVE");
        asset.setCreatedAt(LocalDateTime.now());

        return assetRepository.save(asset);
    }

    @Override
    public List<Asset> getAllAssets() {
        return assetRepository.findAll();
    }

    @Override
    public Asset getAssetById(Long id) {
        return assetRepository.findById(id).orElse(null);
    }

    @Override
    public List<Asset> getAssetsByStatus(String status) {
        return assetRepository.findByStatus(status);
    }
}
