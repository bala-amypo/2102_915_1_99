// src/main/java/com/example/demo/DemoApplication.java
package com.example.demo;

import com.example.demo.entity.*;
import com.example.demo.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;
import java.time.LocalDate;
import java.util.Optional;

@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    // Strict seeding on startup — roles, users, vendor, rule
    @Bean
    CommandLineRunner seedData(RoleRepository roleRepo,
                               UserRepository userRepo,
                               VendorRepository vendorRepo,
                               DepreciationRuleRepository ruleRepo,
                               PasswordEncoder encoder) {
        return args -> {
            // Roles
            Role adminRole = roleRepo.findByName("ADMIN").orElseGet(() -> roleRepo.save(new Role("ADMIN")));
            Role userRole  = roleRepo.findByName("USER").orElseGet(() -> roleRepo.save(new Role("USER")));

            // Admin user
            Optional<User> adminMaybe = userRepo.findByEmail("integration_admin@example.com");
            User admin = adminMaybe.orElseGet(() -> {
                User u = new User();
                u.setName("IntegrationAdmin");
                u.setEmail("integration_admin@example.com");
                u.setPassword(encoder.encode("adminpass"));
                u.setCreatedAt(LocalDateTime.now());
                return u;
            });
            if (admin.getRoles().stream().noneMatch(r -> "ADMIN".equals(r.getName()))) {
                admin.getRoles().add(adminRole);
            }
            userRepo.save(admin);

            // Normal user
            Optional<User> userMaybe = userRepo.findByEmail("integration_user@example.com");
            User normal = userMaybe.orElseGet(() -> {
                User u = new User();
                u.setName("IntegrationUser");
                u.setEmail("integration_user@example.com");
                u.setPassword(encoder.encode("userpass"));
                u.setCreatedAt(LocalDateTime.now());
                return u;
            });
            if (normal.getRoles().stream().noneMatch(r -> "USER".equals(r.getName()))) {
                normal.getRoles().add(userRole);
            }
            userRepo.save(normal);

            // Vendor
            vendorRepo.findByVendorName("IntegrationVendor").orElseGet(() -> {
                Vendor v = new Vendor();
                v.setVendorName("IntegrationVendor");
                v.setContactEmail("vendor@example.com");
                v.setCreatedAt(LocalDateTime.now());
                return vendorRepo.save(v);
            });

            // Depreciation Rule
            ruleRepo.findByRuleName("IntegrationRule").orElseGet(() -> {
                DepreciationRule r = new DepreciationRule();
                r.setRuleName("IntegrationRule");
                r.setMethod("STRAIGHT_LINE");
                r.setUsefulLifeYears(5);
                r.setSalvageValue(10.0);
                r.setCreatedAt(LocalDateTime.now());
                return ruleRepo.save(r);
            });
        };
    }
}
