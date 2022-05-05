package com.mse.showmetheenemyserver.dto;

import com.mse.showmetheenemyserver.domain.Game;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class GameResponseDto {

    private int statusCode;
    private Long id;

    private String firstUsername;

    private String secondUsername;

    @Builder
    public GameResponseDto(int statusCode, Game entity) {
        this.statusCode = statusCode;
        this.id = entity.getId();
        this.firstUsername = entity.getFirstUsername();
        this.secondUsername = entity.getSecondUsername();
    }

}
