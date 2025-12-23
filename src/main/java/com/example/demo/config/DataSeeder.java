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
                Role userRole = new Role("USER");

                roleRepository.save(adminRole);
                roleRepository.save(userRole);
            }

            Role adminRole = roleRepository.findByName("ADMIN").orElseThrow();
            Role userRole = roleRepository.findByName("USER").orElseThrow();

            if (userRepository.count() == 0) {

                User admin = new User(
                        "Admin",
                        "admin",
                        "admin@example.com",
                        passwordEncoder.encode("admin123"),
                        adminRole
                );
                admin.setCreatedAt(LocalDateTime.now());

                User user = new User(
                        "User",
                        "user",
                        "user@example.com",
                        passwordEncoder.encode("user123"),
                        userRole
                );
                user.setCreatedAt(LocalDateTime.now());

                userRepository.save(admin);
                userRepository.save(user);
            }
        };
    }
}
