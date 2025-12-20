package com.example.demo.service;

import com.example.demo.entity.Vendor;

import java.util.List;

public interface VendorService {
    Vendor save(Vendor vendor);
    List<Vendor> findAll();
}
