package com.example.demo.controller;

import com.example.demo.entity.AssetDisposal;
import com.example.demo.service.AssetDisposalService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/disposals")
public class AssetDisposalController {

    private final AssetDisposalService disposalService;

    public AssetDisposalController(AssetDisposalService disposalService) {
        this.disposalService = disposalService;
    }

    @PostMapping("/request/{assetId}")
    public ResponseEntity<AssetDisposal> requestDisposal(@PathVariable Long assetId, 
                                                        @RequestBody AssetDisposal disposal) {
        AssetDisposal created = disposalService.requestDisposal(assetId, disposal);
        return ResponseEntity.ok(created);
    }

    @PutMapping("/approve/{disposalId}/{adminId}")
    public ResponseEntity<AssetDisposal> approveDisposal(@PathVariable Long disposalId, 
                                                        @PathVariable Long adminId) {
        AssetDisposal approved = disposalService.approveDisposal(disposalId, adminId);
        return ResponseEntity.ok(approved);
    }
}