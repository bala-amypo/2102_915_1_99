package com.example.demo.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "vendors")
public class Vendor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String vendorName;

    private String contactEmail;
    private String phone;
    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "vendor")
    private List<Asset> assets;

    public Vendor() {
        this.createdAt = LocalDateTime.now();
    }

    public Vendor(String vendorName, String contactEmail, String phone) {
        this.vendorName = vendorName;
        this.contactEmail = contactEmail;
        this.phone = phone;
        this.createdAt = LocalDateTime.now();
    }

    public Long getId() { return id; }
    public String getVendorName() { return vendorName; }
    public String getContactEmail() { return contactEmail; }
    public String getPhone() { return phone; }
    public LocalDateTime getCreatedAt() { return createdAt; }

    public void setId(Long id) { this.id = id; }
    public void setVendorName(String vendorName) { this.vendorName = vendorName; }
    public void setContactEmail(String contactEmail) { this.contactEmail = contactEmail; }
    public void setPhone(String phone) { this.phone = phone; }
}
