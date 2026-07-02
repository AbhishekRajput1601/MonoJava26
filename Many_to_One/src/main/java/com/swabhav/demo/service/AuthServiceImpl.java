package com.swabhav.demo.service;

import com.swabhav.demo.dto.AuthRequestDto;
import com.swabhav.demo.dto.AuthResponseDto;
import com.swabhav.demo.dto.RegisterRequestDto;
import com.swabhav.demo.exception.DuplicateResourceException;
import com.swabhav.demo.model.AppUser;
import com.swabhav.demo.model.Role;
import com.swabhav.demo.repository.AppUserRepository;
import com.swabhav.demo.security.JwtService;
import com.swabhav.demo.security.UserPrincipal;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Locale;

@Service
@RequiredArgsConstructor
@Transactional
public class AuthServiceImpl implements AuthService {

    private final AppUserRepository appUserRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    @Override
    public AuthResponseDto register(RegisterRequestDto requestDto) {
        if (appUserRepository.existsByUsername(requestDto.getUsername())) {
            throw new DuplicateResourceException("Username already exists: " + requestDto.getUsername());
        }
        if (appUserRepository.existsByEmail(requestDto.getEmail())) {
            throw new DuplicateResourceException("Email already exists: " + requestDto.getEmail());
        }

        Role role = resolveRole(requestDto.getRole());

        AppUser savedUser = appUserRepository.save(AppUser.builder()
                .fullName(requestDto.getFullName())
                .username(requestDto.getUsername())
                .email(requestDto.getEmail())
                .password(passwordEncoder.encode(requestDto.getPassword()))
                .role(role)
                .enabled(true)
                .build());

        UserPrincipal principal = UserPrincipal.fromUser(savedUser);
        String token = jwtService.generateToken(principal);

        return mapToAuthResponse(savedUser, token);
    }

    @Override
    public AuthResponseDto login(AuthRequestDto requestDto) {
        Authentication authentication;
        try {
            authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(requestDto.getUsername(), requestDto.getPassword())
            );
        } catch (BadCredentialsException ex) {
            throw new BadCredentialsException("Invalid username or password");
        }

        UserPrincipal principal = (UserPrincipal) authentication.getPrincipal();
        String token = jwtService.generateToken(principal);

        AppUser user = appUserRepository.findByUsername(principal.getUsername())
                .orElseThrow(() -> new BadCredentialsException("User not found"));

        return mapToAuthResponse(user, token);
    }

    private Role resolveRole(String roleInput) {
        if (roleInput == null || roleInput.isBlank()) {
            return Role.USER;
        }

        try {
            return Role.valueOf(roleInput.trim().toUpperCase(Locale.ROOT));
        } catch (IllegalArgumentException ex) {
            throw new IllegalArgumentException("Invalid role. Allowed values: ADMIN, USER");
        }
    }

    private AuthResponseDto mapToAuthResponse(AppUser user, String token) {
        return AuthResponseDto.builder()
                .userId(user.getId())
                .fullName(user.getFullName())
                .username(user.getUsername())
                .email(user.getEmail())
                .role(user.getRole().name())
                .token(token)
                .tokenType("Bearer")
                .expiresIn(jwtService.getJwtExpirationMs())
                .build();
    }
}

