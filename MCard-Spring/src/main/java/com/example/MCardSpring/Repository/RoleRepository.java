package com.example.MCardSpring.Repository;

import com.example.MCardSpring.MainModel.ERole;
import com.example.MCardSpring.MainModel.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Rol bilgilerinin tutulduğu repository
 */
@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(ERole name);
}
