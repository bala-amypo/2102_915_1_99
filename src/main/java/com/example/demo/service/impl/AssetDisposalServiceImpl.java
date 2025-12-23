package com.example.demo.service.impl;

import com.example.demo.entity.Asset;
import com.example.demo.entity.AssetDisposal;
import com.example.demo.repository.AssetDisposalRepository;
import com.example.demo.repository.AssetRepository;
import com.example.demo.service.AssetDisposalService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.NoSuchElementException;

@Service
public class AssetDisposalServiceImpl implements AssetDisposalService {

    private final AssetRepository assetRepository;
    private final AssetDisposalRepository disposalRepository;

    public AssetDisposalServiceImpl(AssetRepository assetRepository,
                                    AssetDisposalRepository disposalRepository) {
        this.assetRepository = assetRepository;
        this.disposalRepository = disposalRepository;
    }

    @Override
    public AssetDisposal requestDisposal(Long assetId, AssetDisposal disposal) {

        Asset asset = assetRepository.findById(assetId)
                .orElseThrow(() -> new NoSuchElementException("Asset not found"));

        disposal.setAsset(asset);
        disposal.setRequestedAt(LocalDateTime.now());
        disposal.setStatus("REQUESTED");

        return disposalRepository.save(disposal);
    }
}
