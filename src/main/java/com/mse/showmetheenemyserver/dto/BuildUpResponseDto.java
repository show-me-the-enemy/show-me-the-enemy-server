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

    private String type;

    private String name;

    private Integer count;

    @Builder
    public BuildUpResponseDto(String sender, GameStatus status, String type, String name, Integer count) {
        this.sender = sender;
        this.status = status;
        this.type = type;
        this.name = name;
        this.count = count;
    }
}
