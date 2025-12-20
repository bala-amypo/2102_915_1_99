package com.example.demo.controller;

import com.example.demo.entity.Asset;
import com.example.demo.entity.DepreciationRule;
import com.example.demo.entity.Vendor;
import com.example.demo.repository.AssetRepository;
import com.example.demo.repository.DepreciationRuleRepository;
import com.example.demo.repository.VendorRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/assets")
public class AssetController {

    private final AssetRepository assetRepository;
    private final VendorRepository vendorRepository;
    private final DepreciationRuleRepository ruleRepository;

    public AssetController(AssetRepository assetRepository,
                           VendorRepository vendorRepository,
                           DepreciationRuleRepository ruleRepository) {
        this.assetRepository = assetRepository;
        this.vendorRepository = vendorRepository;
        this.ruleRepository = ruleRepository;
    }

    @PostMapping("/{vendorId}/{ruleId}")
    public Asset create(@PathVariable Long vendorId,
                        @PathVariable Long ruleId,
                        @RequestBody Asset asset) {

        Vendor vendor = vendorRepository.findById(vendorId).orElseThrow();
        DepreciationRule rule = ruleRepository.findById(ruleId).orElseThrow();

        asset.setStatus("ACTIVE");
        asset.setVendor(vendor);
        asset.setDepreciationRule(rule);

        return assetRepository.save(asset);
    }

    @GetMapping
    public List<Asset> getAll() {
        return assetRepository.findAll();
    }

    @GetMapping("/{id}")
    public Asset getById(@PathVariable Long id) {
        return assetRepository.findById(id).orElseThrow();
    }

    @GetMapping("/status/{status}")
    public List<Asset> byStatus(@PathVariable String status) {
        return assetRepository.findByStatus(status);
    }
}
