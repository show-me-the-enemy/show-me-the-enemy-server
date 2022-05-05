package com.mse.showmetheenemyserver.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mse.showmetheenemyserver.domain.Game;
import com.mse.showmetheenemyserver.domain.GameMessage;
import com.mse.showmetheenemyserver.service.game.GameService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

@Slf4j
@RequiredArgsConstructor
@Component
public class WebSocketHandler extends TextWebSocketHandler {

    private final ObjectMapper objectMapper;
    private final GameService gameService;

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        String payload = message.getPayload();
        log.info("payload {}", payload);
        GameMessage gameMessage = objectMapper.readValue(payload, GameMessage.class);
        Game game = gameService.findGameById(gameMessage.getGameId());
//        game.handleActions(session, gameMessage, gameService);
    }
}
