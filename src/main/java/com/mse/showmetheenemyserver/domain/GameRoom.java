package com.mse.showmetheenemyserver.domain;

import com.mse.showmetheenemyserver.service.game.GameService;
import lombok.Builder;
import lombok.Getter;
import org.springframework.web.socket.WebSocketSession;
import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Entity
public class GameRoom {

    @Id
    private String roomId;

    private String name;
    private Set<WebSocketSession> sessions = new HashSet<>();

    @Builder
    public GameRoom(String roomId) {
        this.roomId = roomId;
    }

    public void handleActions(WebSocketSession session, GameMessage gameMessage, GameService gameService) {
        if (gameMessage.getType().equals(GameMessage.MessageType.ENTER)) {
            sessions.add(session);
            gameMessage.setMessage(gameMessage.getSender() + "님이 입장했습니다.");
        }
        sendMessage(gameMessage, gameService);
    }

    public <T> void sendMessage(T message, GameService gameService) {
        sessions.parallelStream().forEach(session -> gameService.sendMessage(session, message));
    }
}