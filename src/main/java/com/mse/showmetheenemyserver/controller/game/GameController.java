package com.mse.showmetheenemyserver.controller.game;

import com.mse.showmetheenemyserver.domain.Game;
import com.mse.showmetheenemyserver.dto.GameRequestDto;
import com.mse.showmetheenemyserver.dto.GameResponseDto;
import com.mse.showmetheenemyserver.service.game.GameService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;


@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/game")
@RestController
public class GameController {

    private final GameService gameService;
    private final SimpMessagingTemplate template;

    @PostMapping("/start/{username}")
    public ResponseEntity<GameResponseDto> start(@PathVariable String username) {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/game/start/" + username).toUriString());
        return ResponseEntity.created(uri).body(gameService.createRoom(username));
    }

    @PostMapping("/connect/random/{username}")
    public ResponseEntity<GameResponseDto> connect(@PathVariable String username) {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/game/connect/random/" + username).toUriString());
        return ResponseEntity.created(uri).body(gameService.connectToRandomGame(username));
    }

    @PostMapping("/play")
    public ResponseEntity<GameResponseDto> gamePlay(@RequestBody GameRequestDto requestDto) {
        log.info("gameplay: {}", requestDto);
        Game game = gameService.gamePlay(requestDto);
        template.convertAndSend("/sub/game-progress/" + requestDto.getId(), game);
        return ResponseEntity.ok(GameResponseDto.builder()
                .statusCode(HttpStatus.OK.value())
                .entity(game)
                .build());
    }
}