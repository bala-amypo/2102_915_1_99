package com.example.demo.service.impl;

import com.example.demo.entity.*;
import com.example.demo.repository.*;
import com.example.demo.service.AssetDisposalService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class AssetDisposalServiceImpl implements AssetDisposalService {

    private final AssetRepository assetRepository;
    private final AssetDisposalRepository disposalRepository;
    private final UserRepository userRepository;

    public AssetDisposalServiceImpl(
            AssetRepository assetRepository,
            AssetDisposalRepository disposalRepository,
            UserRepository userRepository
    ) {
        this.assetRepository = assetRepository;
        this.disposalRepository = disposalRepository;
        this.userRepository = userRepository;
    }

    @Override
    public AssetDisposal requestDisposal(Long assetId, Long userId) {

        Asset asset = assetRepository.findById(assetId)
                .orElseThrow();

        User user = userRepository.findById(userId)
                .orElseThrow();

        AssetDisposal disposal = new AssetDisposal();
        disposal.setAsset(asset);
        disposal.setRequestedBy(user);
        disposal.setStatus("REQUESTED");
        disposal.setRequestedAt(LocalDateTime.now());

        return disposalRepository.save(disposal);
    }

    @Override
    public AssetDisposal approveDisposal(Long disposalId, Long adminUserId) {

        AssetDisposal disposal = disposalRepository.findById(disposalId)
                .orElseThrow();

        User admin = userRepository.findById(adminUserId)
                .orElseThrow();

        disposal.setApprovedBy(admin);
        disposal.setApprovedAt(LocalDateTime.now());
        disposal.setStatus("APPROVED");

        return disposalRepository.save(disposal);
    }
}
