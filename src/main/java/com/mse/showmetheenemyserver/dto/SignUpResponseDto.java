package com.mse.showmetheenemyserver.dto;

import com.mse.showmetheenemyserver.domain.User;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SignUpResponseDto {

    private int statusCode;
    private String username;

    public SignUpResponseDto(int statusCode, User entity) {
        this.statusCode = statusCode;
        this.username = entity.getUsername();
    }
}
