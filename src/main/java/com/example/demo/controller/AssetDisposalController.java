// src/main/java/com/example/demo/controller/AssetDisposalController.java
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
    public ResponseEntity<AssetDisposal> request(@PathVariable Long assetId,
                                                 @RequestBody AssetDisposal disposal) {
        return ResponseEntity.ok(disposalService.requestDisposal(assetId, disposal));
    }

    @PutMapping("/approve/{disposalId}/{adminId}")
    public ResponseEntity<AssetDisposal> approve(@PathVariable Long disposalId,
                                                 @PathVariable Long adminId) {
        return ResponseEntity.ok(disposalService.approveDisposal(disposalId, adminId));
    }
}
