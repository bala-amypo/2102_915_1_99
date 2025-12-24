// src/main/java/com/example/demo/controller/VendorController.java
package com.example.demo.controller;

import com.example.demo.entity.Vendor;
import com.example.demo.service.VendorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/vendors")
public class VendorController {

    private final VendorService vendorService;

    public VendorController(VendorService vendorService) {
        this.vendorService = vendorService;
    }

    @PostMapping
    public ResponseEntity<Vendor> create(@RequestBody Vendor vendor) {
        return ResponseEntity.ok(vendorService.createVendor(vendor));
    }

    @GetMapping
    public ResponseEntity<List<Vendor>> all() {
        return ResponseEntity.ok(vendorService.getAllVendors());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> one(@PathVariable Long id) {
        return vendorService.getAllVendors().stream()
                .filter(v -> v.getId().equals(id))
                .findFirst()
                .<ResponseEntity<?>>map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(404).body("Vendor not found"));
    }
}
