package com.peekaboo.user.service.impl;

import com.peekaboo.common.exception.DuplicateResourceException;
import com.peekaboo.common.exception.ResourceNotFoundException;
import com.peekaboo.role.entity.Role;
import com.peekaboo.role.repository.RoleRepository;
import com.peekaboo.user.dto.UserRegistrationRequestDto;
import com.peekaboo.user.dto.UserResponseDto;
import com.peekaboo.user.entity.User;
import com.peekaboo.user.repository.UserRepository;
import com.peekaboo.user.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;


@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository,
                           PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserResponseDto registerUser(UserRegistrationRequestDto request) {

        if (userRepository.existsByEmail(request.getEmail())) {
            throw new DuplicateResourceException("Email already exists: " + request.getEmail());
        }

        Role role = roleRepository.findByName(request.getRoleName())
                .orElseThrow(() -> new ResourceNotFoundException("Role not found: " + request.getRoleName()));

        User user = new User();
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(role);
        user.setIsActive(true);
        user.setIsVerified(false);

        User savedUser = userRepository.save(user);

        return new UserResponseDto(
                savedUser.getId(),
                savedUser.getEmail(),
                savedUser.getRole().getName().name(),
                savedUser.getIsActive(),
                savedUser.getIsVerified()
        );
    }

}