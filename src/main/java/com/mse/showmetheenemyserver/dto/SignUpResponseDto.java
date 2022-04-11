package com.mse.showmetheenemyserver.dto;

import com.mse.showmetheenemyserver.domain.User;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SignUpResponseDto {

    private String username;

    public SignUpResponseDto(User entity) {
        this.username = entity.getUsername();
    }
}
