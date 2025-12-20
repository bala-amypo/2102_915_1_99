package com.example.demo.controller;

import com.example.demo.entity.Vendor;
import com.example.demo.repository.VendorRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/vendors")
public class VendorController {

    private final VendorRepository vendorRepository;

    public VendorController(VendorRepository vendorRepository) {
        this.vendorRepository = vendorRepository;
    }

    @PostMapping
    public Vendor create(@RequestBody Vendor vendor) {
        return vendorRepository.save(vendor);
    }

    @GetMapping
    public List<Vendor> getAll() {
        return vendorRepository.findAll();
    }
}
