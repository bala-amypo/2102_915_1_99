package com.example.demo.controller;

import org.springframework.web.bind.annotation.*;

import com.example.demo.entity.AssetDisposal;
import com.example.demo.service.AssetDisposalService;

@RestController
@RequestMapping("/api/disposals")
public class AssetDisposalController {

    private final AssetDisposalService assetDisposalService;

    public AssetDisposalController(AssetDisposalService assetDisposalService) {
        this.assetDisposalService = assetDisposalService;
    }

    @PostMapping("/request/{assetId}")
    public AssetDisposal requestDisposal(
            @PathVariable Long assetId,
            @RequestBody AssetDisposal disposal) {
        return assetDisposalService.requestDisposal(assetId, disposal);
    }

    @PutMapping("/approve/{disposalId}/{adminId}")
    public AssetDisposal approveDisposal(
            @PathVariable Long disposalId,
            @PathVariable Long adminId) {
        return assetDisposalService.approveDisposal(disposalId, adminId);
    }
}
