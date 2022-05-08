package com.mse.showmetheenemyserver.dto;

import com.mse.showmetheenemyserver.domain.User;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SignUpRequestDto {
    @NotBlank()
    @Size(min = 6, max = 20, message = "Username must be at least 6 characters long!")
    private String username;

    @NotBlank()
    @Size(min = 8, max = 30, message = "Password must be at least 8 characters long!")
    private String password;

    @NotBlank()
    @Size(min = 8, max = 30, message = "Matching password must be at least 8 characters long!")
    private String matchingPassword;

    @Builder
    public SignUpRequestDto(String username, String password, String matchingPassword) {
        this.username = username;
        this.password = password;
        this.matchingPassword = matchingPassword;
    }

    public boolean isDifferentTwoPassword() {
        return !password.equals(matchingPassword);
    }

    public User toEntity() {
        return User
                .builder()
                .username(username)
                .password(password)
                .build();
    }
}