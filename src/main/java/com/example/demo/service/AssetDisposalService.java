package com.example.demo.service;

import com.example.demo.entity.AssetDisposal;

public interface AssetDisposalService {

    AssetDisposal requestDisposal(Long assetId, Long userId);

    AssetDisposal approveDisposal(Long disposalId, Long adminUserId);
}
