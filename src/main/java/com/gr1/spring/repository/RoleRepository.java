package com.gr1.spring.repository;

import java.util.Optional;

import com.gr1.spring.entity.ERole;
import com.gr1.spring.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(String name);
}