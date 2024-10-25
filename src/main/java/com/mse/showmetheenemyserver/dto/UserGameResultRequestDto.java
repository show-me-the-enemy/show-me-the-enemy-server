package com.mse.showmetheenemyserver.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserGameResultRequestDto {

    Long gameId;

    String username;

    Integer numRound;

    Integer crystal;

    Boolean won;
}
