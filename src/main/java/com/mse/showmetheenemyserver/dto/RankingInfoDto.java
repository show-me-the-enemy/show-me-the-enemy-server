package com.mse.showmetheenemyserver.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RankingInfoDto {

    Integer rank;
    String username;
    Integer numWins;
    Integer maxRound;

    @Builder
    public RankingInfoDto(Object[] user) {
        this.username = user[0].toString();
        this.numWins = Integer.parseInt(user[1].toString());
        this.maxRound = Integer.parseInt(user[2].toString());
        this.rank = Integer.parseInt(user[3].toString());
    }
}
