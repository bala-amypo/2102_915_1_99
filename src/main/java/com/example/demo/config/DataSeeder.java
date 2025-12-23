package com.example.demo.config;

import com.example.demo.entity.*;
import com.example.demo.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Configuration
public class DataSeeder {
    
    @Bean
    public CommandLineRunner seedData(RoleRepository roleRepository, 
                                     UserRepository userRepository,
                                     VendorRepository vendorRepository,
                                     DepreciationRuleRepository ruleRepository,
                                     AssetRepository assetRepository,
                                     PasswordEncoder passwordEncoder) {
        return args -> {
            // Seed Roles
            Role adminRole = roleRepository.findByName("ADMIN")
                .orElseGet(() -> roleRepository.save(new Role("ADMIN")));
            
            Role userRole = roleRepository.findByName("USER")
                .orElseGet(() -> roleRepository.save(new Role("USER")));
            
            // Seed Admin User
            if (userRepository.findByEmail("integration_admin@example.com").isEmpty()) {
                User admin = new User();
                admin.setEmail("integration_admin@example.com");
                admin.setName("IntegrationAdmin");
                admin.setPassword(passwordEncoder.encode("adminpass"));
                Set<Role> adminRoles = new HashSet<>();
                adminRoles.add(adminRole);
                admin.setRoles(adminRoles);
                userRepository.save(admin);
            }
            
            // Seed Normal User
            if (userRepository.findByEmail("integration_user@example.com").isEmpty()) {
                User normalUser = new User();
                normalUser.setEmail("integration_user@example.com");
                normalUser.setName("IntegrationUser");
                normalUser.setPassword(passwordEncoder.encode("userpass"));
                Set<Role> normalRoles = new HashSet<>();
                normalRoles.add(userRole);
                normalUser.setRoles(normalRoles);
                userRepository.save(normalUser);
            }
            
            // Seed Vendor
            Vendor vendor = vendorRepository.findByVendorName("IntegrationVendor")
                .orElseGet(() -> {
                    Vendor v = new Vendor();
                    v.setVendorName("IntegrationVendor");
                    v.setContactEmail("vendor@example.com");
                    return vendorRepository.save(v);
                });
            
            // Seed Depreciation Rule
            DepreciationRule rule = ruleRepository.findByRuleName("IntegrationRule")
                .orElseGet(() -> {
                    DepreciationRule r = new DepreciationRule();
                    r.setRuleName("IntegrationRule");
                    r.setMethod("STRAIGHT_LINE");
                    r.setUsefulLifeYears(5);
                    r.setSalvageValue(10.0);
                    return ruleRepository.save(r);
                });
        };
    }
}