package com.mse.showmetheenemyserver.dto;

import com.mse.showmetheenemyserver.domain.GameStatus;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BuildUpRequestDto {

    private Long id;

    private String sender;

    private Integer numMonsters;

    private Integer numItem;
}
