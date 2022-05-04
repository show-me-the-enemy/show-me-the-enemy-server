package com.mse.showmetheenemyserver.service.game;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mse.showmetheenemyserver.domain.GameRoom;
import com.mse.showmetheenemyserver.dto.RoomDto;
import com.mse.showmetheenemyserver.repository.GameRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import java.io.IOException;
import java.util.*;
import static org.springframework.http.HttpStatus.CREATED;
@Slf4j
@RequiredArgsConstructor
@Service
public class GameService {

    private final ObjectMapper objectMapper;

    private final GameRepository gameRepository;

    public GameRoom findRoomById(String roomId) {
        return gameRepository.findById(roomId).get();
    }

    public List<GameRoom> findAllRoom() {
        return gameRepository.findAll();
    }

//    public ChatRoom findRoomById(String roomId) {
//        return chatRooms.get(roomId);
//    }

    public RoomDto createRoom() {
        String randomId = UUID.randomUUID().toString();

        GameRoom newRoom = gameRepository.save(GameRoom
                .builder()
                .roomId(randomId)
                .build()
        );
        return new RoomDto(CREATED.value(), newRoom);
    }

    public <T> void sendMessage(WebSocketSession session, T message) {
        try {
            session.sendMessage(new TextMessage(objectMapper.writeValueAsString(message)));
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }
    }

}
