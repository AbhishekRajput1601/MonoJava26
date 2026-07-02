package com.swabhav.demo.service;

import com.swabhav.demo.dto.AuthRequestDto;
import com.swabhav.demo.dto.AuthResponseDto;
import com.swabhav.demo.dto.RegisterRequestDto;

public interface AuthService {

    AuthResponseDto register(RegisterRequestDto requestDto);
    AuthResponseDto login(AuthRequestDto requestDto);

}

