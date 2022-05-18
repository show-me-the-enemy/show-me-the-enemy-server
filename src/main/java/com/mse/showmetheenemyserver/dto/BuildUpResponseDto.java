package com.mse.showmetheenemyserver.dto;

import com.mse.showmetheenemyserver.domain.GameStatus;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BuildUpResponseDto {

    private String sender;

    private GameStatus status;

    private Integer numMonsters;

    private Integer numItem;

    @Builder
    public BuildUpResponseDto(String sender, GameStatus status, Integer numMonsters, Integer numItem) {
        this.sender = sender;
        this.status = status;
        this.numMonsters = numMonsters;
        this.numItem = numItem;
    }
}
