package com.example.demo.service.impl;

import com.example.demo.entity.Vendor;
import com.example.demo.repository.VendorRepository;
import com.example.demo.service.VendorService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class VendorServiceImpl implements VendorService {

    private final VendorRepository vendorRepository;

    public VendorServiceImpl(VendorRepository vendorRepository) {
        this.vendorRepository = vendorRepository;
    }

    @Override
    public Vendor createVendor(Vendor vendor) {
        // Normalize name for case-insensitive uniqueness
        String normalizedName = vendor.getVendorName().trim().toLowerCase();
        if (vendorRepository.existsByVendorNameIgnoreCase(normalizedName)) {
            throw new IllegalArgumentException("Vendor with name '" + vendor.getVendorName() + "' already exists");
        }
        vendor.setVendorName(normalizedName);
        vendor.setCreatedAt(LocalDateTime.now());
        vendor.setUpdatedAt(LocalDateTime.now());
        return vendorRepository.save(vendor);
    }

    @Override
    public Vendor getVendorById(Long id) {
        return vendorRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Vendor not found with id " + id));
    }

    @Override
    public List<Vendor> getAllVendors() {
        return vendorRepository.findAll();
    }

    @Override
    public void deleteVendor(Long id) {
        Vendor vendor = vendorRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Vendor not found with id " + id));
        vendorRepository.delete(vendor);
    }
}
