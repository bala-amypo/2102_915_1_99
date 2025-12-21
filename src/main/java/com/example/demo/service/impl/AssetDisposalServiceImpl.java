package com.example.demo.service.impl;

import com.example.demo.entity.Asset;
import com.example.demo.entity.AssetDisposal;
import com.example.demo.entity.User;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.AssetDisposalRepository;
import com.example.demo.repository.AssetRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.AssetDisposalService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@Transactional
public class AssetDisposalServiceImpl implements AssetDisposalService {

    private final AssetDisposalRepository disposalRepository;
    private final AssetRepository assetRepository;
    private final UserRepository userRepository;

    public AssetDisposalServiceImpl(
            AssetDisposalRepository disposalRepository,
            AssetRepository assetRepository,
            UserRepository userRepository) {

        this.disposalRepository = disposalRepository;
        this.assetRepository = assetRepository;
        this.userRepository = userRepository;
    }

    @Override
    public AssetDisposal requestDisposal(Long assetId, AssetDisposal disposal) {

        Asset asset = assetRepository.findById(assetId)
                .orElseThrow(() -> new ResourceNotFoundException("Asset not found"));

        if (disposal.getDisposalValue() == null || disposal.getDisposalValue() < 0) {
            throw new IllegalArgumentException("Disposal value cannot be negative");
        }

        disposal.setAsset(asset);
        disposal.setCreatedAt(LocalDateTime.now());

        return disposalRepository.save(disposal);
    }

    @Override
    public AssetDisposal approveDisposal(Long disposalId, Long adminId) {

        AssetDisposal disposal = disposalRepository.findById(disposalId)
                .orElseThrow(() -> new ResourceNotFoundException("Disposal not found"));

        User admin = userRepository.findById(adminId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        boolean isAdmin = admin.getRoles().stream()
                .anyMatch(r -> "ADMIN".equals(r.getName()));

        if (!isAdmin) {
            throw new IllegalArgumentException("Only admins can approve disposal");
        }

        disposal.setApprovedBy(admin);

        Asset asset = disposal.getAsset();
        asset.setStatus("DISPOSED");
        assetRepository.save(asset);

        return disposalRepository.save(disposal);
    }
}
