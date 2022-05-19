package com.mse.showmetheenemyserver.controller.game;

import com.mse.showmetheenemyserver.domain.GameStatus;
import com.mse.showmetheenemyserver.dto.BuildUpResponseDto;
import com.mse.showmetheenemyserver.dto.GameResponseDto;
import com.mse.showmetheenemyserver.service.game.GameService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.core.MessagePostProcessor;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;


@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/games")
@RestController
public class GameController {

    private final GameService gameService;
    private final SimpMessagingTemplate template; // Broker로 메시지를 전달하는 객체
    private SimpMessageHeaderAccessor accessor;
    @PostMapping("/start/{username}")
    public ResponseEntity<GameResponseDto> start(@PathVariable String username) {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/games/start/" + username).toUriString());
        return ResponseEntity.created(uri).body(gameService.createGame(username));
    }

    @PostMapping("/connect/random/{username}")
    public ResponseEntity<GameResponseDto> connectToRandomGame(@PathVariable String username) {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/games/connect/random/" + username).toUriString());

        GameResponseDto responseDto = gameService.connectToRandomGame(username);
        log.info("Notify game number {} subscribers that {} has participated in the game", responseDto.getId(), responseDto.getSecondUsername());
        accessor.setHeader("game-status", "start");
        template.convertAndSend("/sub/games/" + responseDto.getId(), responseDto,  accessor.toMap());

        return ResponseEntity.created(uri).body(responseDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<GameResponseDto> updateGameStatusToFinished(@PathVariable Long id) {
        log.info("Game '{}' is over so that change status to FINISHED", id);
        GameResponseDto gameResponseDto = gameService.updateGameStatusToFinished(id);
        template.convertAndSend(
                "/sub/games/" + id,
                BuildUpResponseDto.builder()
                        .sender("ADMIN")
                        .status(gameResponseDto.getStatus())
                        .numMonsters(0)
                        .numItem(0)
                        .build()
        );

        return ResponseEntity.ok(gameResponseDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteByGameId(@PathVariable Long id) {
        gameService.delete(id);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping()
    public void deleteAll() {
        gameService.deleteAll();
    }
}