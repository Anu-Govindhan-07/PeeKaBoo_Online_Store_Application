package com.peekaboo.role.repository;

import com.peekaboo.role.entity.Role;
import com.peekaboo.role.enums.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(RoleName name);

    boolean existsByName(RoleName name);
}
