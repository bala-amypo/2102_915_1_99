package com.example.demo.repository;

import com.example.demo.entity.Vendor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VendorRepository extends JpaRepository<Vendor, Long> {

    boolean existsByVendorNameIgnoreCase(String vendorName);

    Optional<Vendor> findByVendorNameIgnoreCase(String vendorName);

    // Add this to satisfy tests that expect findByVendorName(String)
    Optional<Vendor> findByVendorName(String vendorName);
}
