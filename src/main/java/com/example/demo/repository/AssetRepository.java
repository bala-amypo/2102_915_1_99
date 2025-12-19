package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Asset;
import com.example.demo.entity.Vendor;

public interface AssetRepository extends JpaRepository<Asset, Long> {

    boolean existsByAssetTag(String assetTag);

    List<Asset> findByStatus(String status);

    List<Asset> findByVendor(Vendor vendor);
}
