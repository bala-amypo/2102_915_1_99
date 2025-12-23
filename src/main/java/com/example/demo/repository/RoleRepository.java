package com.example.demo.repository;

import com.example.demo.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    // Case-insensitive lookup
    Optional<Role> findByNameIgnoreCase(String name);

    // Still keep the original if needed
    Optional<Role> findByName(String name);
}
