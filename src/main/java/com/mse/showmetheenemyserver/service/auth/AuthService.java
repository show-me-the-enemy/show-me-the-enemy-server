package com.mse.showmetheenemyserver.service.auth;

import com.mse.showmetheenemyserver.dto.SignUpRequestDto;
import com.mse.showmetheenemyserver.dto.SignUpResponseDto;

public interface AuthService {
    SignUpResponseDto registerUser(SignUpRequestDto requestDto);
}
