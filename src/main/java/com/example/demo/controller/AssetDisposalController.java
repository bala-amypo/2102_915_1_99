// AssetDisposalController.java
package com.example.demo.controller;

import com.example.demo.entity.AssetDisposal;
import com.example.demo.service.AssetDisposalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/disposals")
public class AssetDisposalController {

    @Autowired
    private AssetDisposalService disposalService;

    @PutMapping("/approve/{disposalId}/{adminId}")
    public AssetDisposal approveDisposal(
            @PathVariable Long disposalId,
            @PathVariable Long adminId
    ) {
        return disposalService.approveDisposal(disposalId, adminId);
    }
}
                 