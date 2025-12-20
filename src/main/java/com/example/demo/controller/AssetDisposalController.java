package com.example.demo.controller;

import com.example.demo.entity.Asset;
import com.example.demo.entity.AssetDisposal;
import com.example.demo.entity.User;
import com.example.demo.repository.AssetDisposalRepository;
import com.example.demo.repository.AssetRepository;
import com.example.demo.repository.UserRepository;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/disposals")
public class AssetDisposalController {

    private final AssetDisposalRepository disposalRepository;
    private final AssetRepository assetRepository;
    private final UserRepository userRepository;

    public AssetDisposalController(AssetDisposalRepository disposalRepository,
                                   AssetRepository assetRepository,
                                   UserRepository userRepository) {
        this.disposalRepository = disposalRepository;
        this.assetRepository = assetRepository;
        this.userRepository = userRepository;
    }

    @PostMapping("/request/{assetId}")
    public AssetDisposal request(@PathVariable Long assetId,
                                 @RequestBody AssetDisposal disposal) {

        Asset asset = assetRepository.findById(assetId).orElseThrow();
        disposal.setAsset(asset);
        disposal.setCreatedAt(LocalDateTime.now());

        return disposalRepository.save(disposal);
    }

    @PutMapping("/approve/{disposalId}/{adminId}")
    public AssetDisposal approve(@PathVariable Long disposalId,
                                 @PathVariable Long adminId) {

        AssetDisposal disposal = disposalRepository.findById(disposalId).orElseThrow();
        User admin = userRepository.findById(adminId).orElseThrow();

        disposal.setApprovedBy(admin);
        disposal.getAsset().setStatus("DISPOSED");

        return disposalRepository.save(disposal);
    }
}
