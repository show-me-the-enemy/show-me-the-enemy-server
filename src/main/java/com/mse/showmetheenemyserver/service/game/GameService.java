package com.mse.showmetheenemyserver.service.game;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mse.showmetheenemyserver.domain.Game;
import com.mse.showmetheenemyserver.dto.GameRequestDto;
import com.mse.showmetheenemyserver.dto.GameResponseDto;
import com.mse.showmetheenemyserver.exception.GameNotFoundException;
import com.mse.showmetheenemyserver.repository.GameRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.mse.showmetheenemyserver.domain.GameStatus.IN_PROGRESS;
import static com.mse.showmetheenemyserver.domain.GameStatus.NEW;
import static org.springframework.http.HttpStatus.CREATED;
@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class GameService {

    private final ObjectMapper objectMapper;

    private final GameRepository gameRepository;

    public Game findGameById(Long id) {
        log.info("find game by {}", id);
        return gameRepository.findById(id).orElseThrow(GameNotFoundException::new);
    }

    public List<Game> findAllGames() {
        return gameRepository.findAll();
    }

//    public ChatRoom findRoomById(String roomId) {
//        return chatRooms.get(roomId);
//    }

    @Transactional
    public GameResponseDto createGame(String username) {

        Game newGame = Game.builder()
                .firstUsername(username)
                .status(NEW)
                .build();

        log.info("{} start a new game", username);
        gameRepository.save(newGame);

        return new GameResponseDto(CREATED.value(), newGame);
    }

    @Transactional
    public GameResponseDto connectToRandomGame(String username) {
        log.info("Find newly created games.");
        Game newGame = gameRepository.findAllNewGames()
                .flatMap(games -> games.stream().findFirst())
                .orElseThrow(GameNotFoundException::new);

        log.info("Connect {} into the game {}", username, newGame.getId());
        newGame.connectSecondUser(username);
        newGame.changeGameStatus(IN_PROGRESS);

        return new GameResponseDto(CREATED.value(), newGame);
    }

    public Game gamePlay(GameRequestDto requestDto) {
        Game game = gameRepository.findById(requestDto.getId()).orElseThrow(GameNotFoundException::new);

        // TODO: FINISH 체크

        return game;
    }

//    public <T> void sendMessage(WebSocketSession session, T message) {
//        try {
//            session.sendMessage(new TextMessage(objectMapper.writeValueAsString(message)));
//        } catch (IOException e) {
//            log.error(e.getMessage(), e);
//        }
//    }
}
