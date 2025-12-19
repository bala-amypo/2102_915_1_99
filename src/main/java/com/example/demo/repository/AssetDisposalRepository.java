package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.AssetDisposal;
import com.example.demo.entity.User;

public interface AssetDisposalRepository extends JpaRepository<AssetDisposal, Long> {

    List<AssetDisposal> findByApprovedBy(User approvedBy);
}
