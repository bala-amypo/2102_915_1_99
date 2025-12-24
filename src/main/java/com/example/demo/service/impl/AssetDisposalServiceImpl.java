// src/main/java/com/example/demo/service/impl/AssetDisposalServiceImpl.java
package com.example.demo.service.impl;

import com.example.demo.entity.Asset;
import com.example.demo.entity.AssetDisposal;
import com.example.demo.entity.Role;
import com.example.demo.entity.User;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.AssetDisposalRepository;
import com.example.demo.repository.AssetRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.AssetDisposalService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class AssetDisposalServiceImpl implements AssetDisposalService {

    private final AssetDisposalRepository disposalRepo;
    private final AssetRepository assetRepo;
    private final UserRepository userRepo;

    public AssetDisposalServiceImpl(AssetDisposalRepository disposalRepo,
                                    AssetRepository assetRepo,
                                    UserRepository userRepo) {
        this.disposalRepo = disposalRepo;
        this.assetRepo = assetRepo;
        this.userRepo = userRepo;
    }

    @Override
    public AssetDisposal requestDisposal(Long assetId, AssetDisposal disposal) {
        Asset asset = assetRepo.findById(assetId)
            .orElseThrow(() -> new ResourceNotFoundException("Asset not found"));
        if (disposal.getDisposalValue() == null || disposal.getDisposalValue() < 0) {
            throw new IllegalArgumentException("Invalid disposal value");
        }
        disposal.setAsset(asset);
        disposal.setCreatedAt(LocalDateTime.now());
        return disposalRepo.save(disposal);
    }

    @Override
    public AssetDisposal approveDisposal(Long disposalId, Long adminId) {
        AssetDisposal disp = disposalRepo.findById(disposalId)
            .orElseThrow(() -> new ResourceNotFoundException("Disposal not found"));
        User admin = userRepo.findById(adminId)
            .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        boolean isAdmin = admin.getRoles().stream().map(Role::getName).anyMatch("ADMIN"::equals);
        if (!isAdmin) {
            throw new IllegalArgumentException("Not authorized");
        }

        disp.setApprovedBy(admin);
        Asset asset = disp.getAsset();
        asset.setStatus("DISPOSED");
        assetRepo.save(asset);
        return disposalRepo.save(disp);
    }
}
