package com.example.demo.service.impl;

import com.example.demo.entity.Vendor;
import com.example.demo.repository.VendorRepository;
import com.example.demo.service.VendorService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VendorServiceImpl implements VendorService {

    private final VendorRepository vendorRepository;

    public VendorServiceImpl(VendorRepository vendorRepository) {
        this.vendorRepository = vendorRepository;
    }

    @Override
    public Vendor createVendor(Vendor vendor) {

        vendorRepository.findByVendorName(vendor.getVendorName())
                .ifPresent(v -> {
                    throw new IllegalArgumentException("Vendor already exists");
                });

        return vendorRepository.save(vendor);
    }

    @Override
    public List<Vendor> getAllVendors() {
        return vendorRepository.findAll();
    }
}
