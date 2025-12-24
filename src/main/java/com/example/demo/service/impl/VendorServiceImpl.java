// src/main/java/com/example/demo/service/impl/VendorServiceImpl.java
package com.example.demo.service.impl;

import com.example.demo.entity.Vendor;
import com.example.demo.repository.VendorRepository;
import com.example.demo.service.VendorService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.regex.Pattern;

@Service
public class VendorServiceImpl implements VendorService {

    private final VendorRepository vendorRepo;

    private static final Pattern EMAIL =
        Pattern.compile("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$");

    public VendorServiceImpl(VendorRepository vendorRepo) {
        this.vendorRepo = vendorRepo;
    }

    @Override
    public Vendor createVendor(Vendor vendor) {
        if (vendor.getVendorName() == null || vendor.getVendorName().isBlank()) {
            throw new IllegalArgumentException("Vendor name required");
        }
        if (vendorRepo.findByVendorName(vendor.getVendorName()).isPresent()) {
            throw new IllegalArgumentException("Duplicate vendor name");
        }
        if (vendor.getContactEmail() == null || !EMAIL.matcher(vendor.getContactEmail()).matches()) {
            throw new IllegalArgumentException("Invalid email");
        }
        vendor.setCreatedAt(LocalDateTime.now());
        return vendorRepo.save(vendor);
    }

    @Override
    public List<Vendor> getAllVendors() {
        return vendorRepo.findAll();
    }
}
