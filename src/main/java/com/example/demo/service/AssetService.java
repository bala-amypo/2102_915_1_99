package com.example.demo.service;

import com.example.demo.entity.Asset;

import java.util.List;
import java.util.Optional;

public interface AssetService {
    Asset save(Asset asset);
    Optional<Asset> findById(Long id);
    List<Asset> findAll();
    List<Asset> findByStatus(String status);
}
