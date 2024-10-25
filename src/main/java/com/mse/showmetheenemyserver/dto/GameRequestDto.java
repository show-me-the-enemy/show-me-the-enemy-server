package com.mse.showmetheenemyserver.dto;

import com.mse.showmetheenemyserver.domain.Game;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class GameRequestDto {

    private Long id;

    private String firstUsername;

    private String secondUsername;

    @Builder
    public GameRequestDto(Game entity) {
        this.id = entity.getId();
        this.firstUsername = entity.getFirstUsername();
        this.secondUsername = entity.getSecondUsername();
    }

    @Override
    public String toString() {
        return "GameRequestDto{" +
                "id=" + id +
                ", firstUsername='" + firstUsername + '\'' +
                ", secondUsername='" + secondUsername + '\'' +
                '}';
    }
}
