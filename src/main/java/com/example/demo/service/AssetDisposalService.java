package com.example.demo.service;

import com.example.demo.entity.AssetDisposal;

import java.util.Optional;

public interface AssetDisposalService {
    AssetDisposal save(AssetDisposal disposal);
    Optional<AssetDisposal> findById(Long id);
}
