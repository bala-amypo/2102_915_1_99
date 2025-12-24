package com.example.demo.repository;

import com.example.demo.entity.AssetLifecycleEvent;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AssetLifecycleEventRepository extends JpaRepository<AssetLifecycleEvent, Long> {
    List<AssetLifecycleEvent> findByAsset_IdOrderByEventDateDesc(Long assetId);
}
