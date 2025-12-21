// AssetDisposalService.java
package com.example.demo.service;

import com.example.demo.entity.AssetDisposal;

public interface AssetDisposalService {
    AssetDisposal approveDisposal(Long disposalId, Long adminId);
}
