package com.example.demo.repository;

import com.example.demo.entity.AssetLifecycleEvent;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface AssetLifecycleEventRepository extends JpaRepository<AssetLifecycleEvent, Long> {

    // Use ONE of these depending on your entity structure:

    // Case A: if AssetLifecycleEvent has a field "assetId"
    List<AssetLifecycleEvent> findByAssetIdOrderByEventDateDesc(Long assetId);

    // Case B: if AssetLifecycleEvent has a field "asset" (relation to Asset entity)
    List<AssetLifecycleEvent> findByAsset_IdOrderByEventDateDesc(Long assetId);
}
