package com.example.demo.config;

import com.example.demo.entity.Role;
import com.example.demo.entity.User;
import com.example.demo.entity.Vendor;
import com.example.demo.entity.DepreciationRule;
import com.example.demo.repository.RoleRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.repository.VendorRepository;
import com.example.demo.repository.DepreciationRuleRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;
import java.util.Set;

@Configuration
public class DataSeeder {

    @Bean
    CommandLineRunner seedData(
            RoleRepository roleRepository,
            UserRepository userRepository,
            VendorRepository vendorRepository,
            DepreciationRuleRepository ruleRepository,
            PasswordEncoder passwordEncoder
    ) {
        return args -> {
            // Ensure roles ADMIN and USER exist
            Role adminRole = roleRepository.findByName("ADMIN")
                    .orElseGet(() -> {
                        Role r = new Role("ADMIN");
                        r.setCreatedAt(LocalDateTime.now());
                        r.setUpdatedAt(LocalDateTime.now());
                        return roleRepository.save(r);
                    });

            Role userRole = roleRepository.findByName("USER")
                    .orElseGet(() -> {
                        Role r = new Role("USER");
                        r.setCreatedAt(LocalDateTime.now());
                        r.setUpdatedAt(LocalDateTime.now());
                        return roleRepository.save(r);
                    });

            // Ensure integration admin user exists
            userRepository.findByEmail("integration_admin@example.com").orElseGet(() -> {
                User admin = new User();
                admin.setName("Integration Admin");
                admin.setEmail("integration_admin@example.com");
                admin.setPassword(passwordEncoder.encode("adminpass"));
                admin.setRoles(Set.of(adminRole));
                admin.setCreatedAt(LocalDateTime.now());
                admin.setUpdatedAt(LocalDateTime.now());
                return userRepository.save(admin);
            });

            // Ensure integration normal user exists
            userRepository.findByEmail("integration_user@example.com").orElseGet(() -> {
                User user = new User();
                user.setName("Integration User");
                user.setEmail("integration_user@example.com");
                user.setPassword(passwordEncoder.encode("userpass"));
                user.setRoles(Set.of(userRole));
                user.setCreatedAt(LocalDateTime.now());
                user.setUpdatedAt(LocalDateTime.now());
                return userRepository.save(user);
            });

            // Ensure vendor exists
            vendorRepository.findByVendorName("IntegrationVendor").orElseGet(() -> {
                Vendor v = new Vendor();
                v.setVendorName("IntegrationVendor");
                v.setContactEmail("vendor@example.com");
                v.setPhone("0000000000");
                v.setCreatedAt(LocalDateTime.now());
                v.setUpdatedAt(LocalDateTime.now());
                return vendorRepository.save(v);
            });

            // Ensure depreciation rule exists
            ruleRepository.findByRuleName("IntegrationRule").orElseGet(() -> {
                DepreciationRule rule = new DepreciationRule();
                rule.setRuleName("IntegrationRule");
                rule.setMethod("STRAIGHT_LINE");
                rule.setUsefulLifeYears(5);
                rule.setSalvageValue(0.0);
                rule.setCreatedAt(LocalDateTime.now());
                rule.setUpdatedAt(LocalDateTime.now());
                return ruleRepository.save(rule);
            });
        };
    }
}
