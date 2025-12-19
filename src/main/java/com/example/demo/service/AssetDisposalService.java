package com.example.demo.service.impl;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Asset;
import com.example.demo.entity.AssetDisposal;
import com.example.demo.entity.User;
import com.example.demo.repository.AssetDisposalRepository;
import com.example.demo.repository.AssetRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.AssetDisposalService;

@Service
public class AssetDisposalServiceImpl implements AssetDisposalService {

    @Autowired
    private AssetDisposalRepository disposalRepository;

    @Autowired
    private AssetRepository assetRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public AssetDisposal requestDisposal(Long assetId, AssetDisposal disposal) {
        Asset asset = assetRepository.findById(assetId).orElse(null);
        disposal.setAsset(asset);
        disposal.setCreatedAt(LocalDateTime.now());
        return disposalRepository.save(disposal);
    }

    @Override
    public AssetDisposal approveDisposal(Long disposalId, Long adminId) {
        AssetDisposal disposal = disposalRepository.findById(disposalId).orElse(null);
        User admin = userRepository.findById(adminId).orElse(null);

        if (disposal != null) {
            disposal.setApprovedBy(admin);
            if (disposal.getAsset() != null) {
                disposal.getAsset().setStatus("DISPOSED");
                assetRepository.save(disposal.getAsset());
            }
            return disposalRepository.save(disposal);
        }
        return null;
    }
}
