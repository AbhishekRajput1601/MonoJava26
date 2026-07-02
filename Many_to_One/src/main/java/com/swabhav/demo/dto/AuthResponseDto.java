package com.swabhav.demo.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AuthResponseDto {

    private Long userId;
    private String fullName;
    private String username;
    private String email;
    private String role;
    private String token;
    private String tokenType;
    private long expiresIn;
}

