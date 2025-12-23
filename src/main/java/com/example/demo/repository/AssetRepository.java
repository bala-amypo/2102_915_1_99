package com.example.demo.repository;

import com.example.demo.entity.Asset;
import com.example.demo.entity.Vendor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface AssetRepository extends JpaRepository<Asset, Long> {
    boolean existsByAssetTagIgnoreCase(String assetTag);
    List<Asset> findByStatusIgnoreCase(String status);
    List<Asset> findByVendor(Vendor vendor);
}
