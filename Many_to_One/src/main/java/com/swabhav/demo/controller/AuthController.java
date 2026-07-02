package com.swabhav.demo.controller;

import com.swabhav.demo.dto.AuthRequestDto;
import com.swabhav.demo.dto.AuthResponseDto;
import com.swabhav.demo.dto.RegisterRequestDto;
import com.swabhav.demo.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@Tag(name = "Authentication", description = "JWT authentication APIs")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    @Operation(summary = "Register a user and issue JWT")
    public ResponseEntity<AuthResponseDto> register(@Valid @RequestBody RegisterRequestDto requestDto) {
        return new ResponseEntity<>(authService.register(requestDto), HttpStatus.CREATED);
    }

    @PostMapping("/login")
    @Operation(summary = "Authenticate and issue JWT")
    public ResponseEntity<AuthResponseDto> login(@Valid @RequestBody AuthRequestDto requestDto) {
        return ResponseEntity.ok(authService.login(requestDto));
    }
}

