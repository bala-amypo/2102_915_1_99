package com.example.demo.entity;

import java.time.LocalDateTime;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;

@Entity
@Table(
    name = "vendors",
    uniqueConstraints = {
        @UniqueConstraint(columnNames = "vendorName")
    }
)
public class Vendor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String vendorName;

    @Email
    @Column(nullable = false)
    private String contactEmail;

    private String phone;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    public Vendor() {
    }

    public Vendor(String vendorName, String contactEmail, String phone) {
        this.vendorName = vendorName;
        this.contactEmail = contactEmail;
        this.phone = phone;
    }

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public String getVendorName() {
        return vendorName;
    }

    public void setVendorName(String vendorName) {
        this.vendorName = vendorName;
    }

    public String getContactEmail() {
        return contactEmail;
    }

    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}
