package com.example.demo.service.impl;

import com.example.demo.entity.AssetDisposal;
import com.example.demo.repository.AssetDisposalRepository;
import com.example.demo.service.AssetDisposalService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AssetDisposalServiceImpl implements AssetDisposalService {

    private final AssetDisposalRepository repository;

    public AssetDisposalServiceImpl(AssetDisposalRepository repository) {
        this.repository = repository;
    }

    @Override
    public AssetDisposal save(AssetDisposal disposal) {
        return repository.save(disposal);
    }

    @Override
    public Optional<AssetDisposal> findById(Long id) {
        return repository.findById(id);
    }
}
