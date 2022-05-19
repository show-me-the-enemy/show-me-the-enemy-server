package com.mse.showmetheenemyserver.dto;

import com.mse.showmetheenemyserver.domain.User;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserGameResultResponseDto {

    Integer statusCode;

    String username;

    Integer maxRound;

    Integer crystal;

    Integer numWins;

    @Builder
    public UserGameResultResponseDto(Integer statusCode, User entity) {
        this.statusCode = statusCode;
        this.username = entity.getUsername();
        this.maxRound = entity.getMaxRound();
        this.crystal = entity.getCrystal();
        this.numWins = entity.getNumWins();
    }
}