package com.example.demo.service;

import com.example.demo.entity.Asset;
import java.util.List;

public interface AssetService {

    Asset createAsset(Long vendorId, Long ruleId, Asset asset);

    List<Asset> getAssetsByStatus(String status);

    List<Asset> getAllAssets();

    Asset getAsset(Long id);

    // alias methods for controller compatibility
    default List<Asset> getAll() {
        return getAllAssets();
    }

    default Asset getById(Long id) {
        return getAsset(id);
    }

    default List<Asset> getByStatus(String status) {
        return getAssetsByStatus(status);
    }
}
