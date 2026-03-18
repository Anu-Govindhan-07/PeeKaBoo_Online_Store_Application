package com.peekaboo.user.repository;

import com.peekaboo.user.entity.CustomerProfile;
import com.peekaboo.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerProfileRepository extends JpaRepository<CustomerProfile, Long> {

    Optional<CustomerProfile> findByUser(User user);

    boolean existsByUser(User user);
}