package com.example.demo.config;

import com.example.demo.entity.Role;
import com.example.demo.entity.User;
import com.example.demo.repository.RoleRepository;
import com.example.demo.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;

@Configuration
public class DataSeeder {

    @Bean
    CommandLineRunner seedData(
            RoleRepository roleRepository,
            UserRepository userRepository,
            PasswordEncoder passwordEncoder
    ) {
        return args -> {

            if (roleRepository.count() == 0) {
                Role adminRole = new Role("ADMIN");
                adminRole.setCreatedAt(LocalDateTime.now());
                adminRole.setUpdatedAt(LocalDateTime.now());

                Role userRole = new Role("USER");
                userRole.setCreatedAt(LocalDateTime.now());
                userRole.setUpdatedAt(LocalDateTime.now());

                roleRepository.save(adminRole);
                roleRepository.save(userRole);
            }

            Role adminRole = roleRepository.findByNameIgnoreCase("ADMIN").orElseThrow();
            Role userRole = roleRepository.findByNameIgnoreCase("USER").orElseThrow();

            if (userRepository.count() == 0) {
                User admin = new User(
                        "Admin",
                        "admin",
                        "admin@example.com",
                        passwordEncoder.encode("admin123"),
                        adminRole
                );
                admin.setCreatedAt(LocalDateTime.now());
                admin.setUpdatedAt(LocalDateTime.now());

                User user = new User(
                        "User",
                        "user",
                        "user@example.com",
                        passwordEncoder.encode("user123"),
                        userRole
                );
                user.setCreatedAt(LocalDateTime.now());
                user.setUpdatedAt(LocalDateTime.now());

                userRepository.save(admin);
                userRepository.save(user);
            }
        };
    }
}
