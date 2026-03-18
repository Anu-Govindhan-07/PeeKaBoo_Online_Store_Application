package com.peekaboo.config;

import com.peekaboo.role.entity.Role;
import com.peekaboo.role.enums.RoleName;
import com.peekaboo.role.repository.RoleRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataInitializer {

    @Bean
    public CommandLineRunner initRoles(RoleRepository roleRepository) {
        return args -> {

            if (!roleRepository.existsByName(RoleName.ADMIN)) {
                roleRepository.save(new Role(RoleName.ADMIN));
            }

            if (!roleRepository.existsByName(RoleName.OPERATOR)) {
                roleRepository.save(new Role(RoleName.OPERATOR));
            }

            if (!roleRepository.existsByName(RoleName.CUSTOMER)) {
                roleRepository.save(new Role(RoleName.CUSTOMER));
            }

            System.out.println("Default roles initialized");
        };
    }
}