package com.mse.showmetheenemyserver.dto;

import com.mse.showmetheenemyserver.domain.GameRoom;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RoomDto {

    private int statusCode;
    private String roomId;

    public RoomDto(int statusCode, GameRoom entity) {
        this.statusCode = statusCode;
        this.roomId = entity.getRoomId();
    }

}
