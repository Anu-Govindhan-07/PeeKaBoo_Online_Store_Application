package com.peekaboo.security.controller;

import com.peekaboo.security.dto.AuthResponseDto;
import com.peekaboo.security.dto.RefreshTokenRequestDto;
import com.peekaboo.security.entity.RefreshToken;
import com.peekaboo.security.service.JwtService;
import com.peekaboo.security.service.RefreshTokenService;
import com.peekaboo.user.dto.LoginRequestDto;
import com.peekaboo.user.entity.User;
import com.peekaboo.user.repository.UserRepository;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final RefreshTokenService refreshTokenService;
    private final UserRepository userRepository;

    public AuthController(AuthenticationManager authenticationManager,
                          JwtService jwtService,
                          RefreshTokenService refreshTokenService,
                          UserRepository userRepository) {
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
        this.refreshTokenService = refreshTokenService;
        this.userRepository = userRepository;
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDto> login(@Valid @RequestBody LoginRequestDto request) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        String role = authentication.getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .findFirst()
                .orElse("ROLE_UNKNOWN");

        String accessToken = jwtService.generateToken(userDetails);

        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));

        RefreshToken refreshToken = refreshTokenService.createOrUpdateRefreshToken(user);

        AuthResponseDto response = new AuthResponseDto(
                accessToken,
                refreshToken.getToken(),
                authentication.getName(),
                role
        );

        return ResponseEntity.ok(response);
    }

    @PostMapping("/refresh")
    public ResponseEntity<AuthResponseDto> refreshToken(@Valid @RequestBody RefreshTokenRequestDto request) {

        RefreshToken refreshToken = refreshTokenService.verifyRefreshToken(request.getRefreshToken());
        User user = refreshToken.getUser();

        UserDetails userDetails = org.springframework.security.core.userdetails.User
                .withUsername(user.getEmail())
                .password(user.getPassword())
                .authorities("ROLE_" + user.getRole().getName().name())
                .build();

        String newAccessToken = jwtService.generateToken(userDetails);

        AuthResponseDto response = new AuthResponseDto(
                newAccessToken,
                refreshToken.getToken(),
                user.getEmail(),
                "ROLE_" + user.getRole().getName().name()
        );

        return ResponseEntity.ok(response);
    }
}