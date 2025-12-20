package com.example.demo.service.impl;

import com.example.demo.entity.Asset;
import com.example.demo.repository.AssetRepository;
import com.example.demo.service.AssetService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AssetServiceImpl implements AssetService {

    private final AssetRepository repository;

    public AssetServiceImpl(AssetRepository repository) {
        this.repository = repository;
    }

    @Override
    public Asset save(Asset asset) {
        return repository.save(asset);
    }

    @Override
    public Optional<Asset> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public List<Asset> findAll() {
        return repository.findAll();
    }

    @Override
    public List<Asset> findByStatus(String status) {
        return repository.findByStatus(status);
    }
}
