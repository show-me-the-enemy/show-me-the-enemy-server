package com.mse.showmetheenemyserver.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class LoginResponseDto {

    private int statusCode;
    private String message;
    private String accessToken;
    private String refreshToken;

    @Builder
    public LoginResponseDto(int statusCode, String message, String accessToken, String refreshToken) {
        this.statusCode = statusCode;
        this.message = message;
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }
}
