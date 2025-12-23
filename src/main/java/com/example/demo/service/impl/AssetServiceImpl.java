package com.example.demo.service.impl;

import com.example.demo.entity.Asset;
import com.example.demo.entity.DepreciationRule;
import com.example.demo.entity.Vendor;
import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.AssetRepository;
import com.example.demo.repository.DepreciationRuleRepository;
import com.example.demo.repository.VendorRepository;
import com.example.demo.service.AssetService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
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

        Vendor vendor = vendorRepository.findById(vendorId)
                .orElseThrow(() -> new ResourceNotFoundException("Vendor not found with id " + vendorId));

        DepreciationRule rule = depreciationRuleRepository.findById(ruleId)
                .orElseThrow(() -> new ResourceNotFoundException("Depreciation rule not found with id " + ruleId));

        if (asset.getPurchaseCost() <= 0) {
            throw new BadRequestException("Purchase cost must be greater than zero");
        }

        if (assetRepository.existsByAssetTagIgnoreCase(asset.getAssetTag())) {
            throw new BadRequestException("Asset tag '" + asset.getAssetTag() + "' already exists");
        }

        asset.setVendor(vendor);
        asset.setDepreciationRule(rule);
        asset.setStatus("ACTIVE");
        asset.setCreatedAt(LocalDateTime.now());
        asset.setUpdatedAt(LocalDateTime.now());

        return assetRepository.save(asset);
    }

    @Override
    public List<Asset> getAllAssets() {
        return assetRepository.findAll();
    }

    @Override
    public Asset getAsset(Long id) {
        return assetRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Asset not found with id " + id));
    }

    @Override
    public List<Asset> getAssetsByStatus(String status) {
        return assetRepository.findByStatusIgnoreCase(status);
    }
}
