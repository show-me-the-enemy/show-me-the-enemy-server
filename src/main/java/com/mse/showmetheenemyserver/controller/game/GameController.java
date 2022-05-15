package com.mse.showmetheenemyserver.controller.game;

import com.mse.showmetheenemyserver.domain.Game;
import com.mse.showmetheenemyserver.dto.GameRequestDto;
import com.mse.showmetheenemyserver.dto.GameResponseDto;
import com.mse.showmetheenemyserver.service.game.GameService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;


@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/games")
@RestController
public class GameController {

    private final GameService gameService;
    private final SimpMessagingTemplate template; // Broker로 메시지를 전달하는 객체

    @PostMapping("/start/{username}")
    public ResponseEntity<GameResponseDto> start(@PathVariable String username) {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/games/start/" + username).toUriString());
        return ResponseEntity.created(uri).body(gameService.createGame(username));
    }

    @PostMapping("/connect/random/{username}")
    public ResponseEntity<GameResponseDto> connectToRandomGame(@PathVariable String username) {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/games/connect/random/" + username).toUriString());

        GameResponseDto responseDto = gameService.connectToRandomGame(username);
        log.info("Notify game {} subscribers that {} has participated in the game", responseDto.getId(), responseDto.getSecondUsername());
        template.convertAndSend("/sub/games/" + responseDto.getId(), responseDto);

        return ResponseEntity.created(uri).body(responseDto);
    }

    @DeleteMapping()
    public void deleteAll() {
        gameService.deleteAll();
    }

    @MessageMapping("/play")
    public void gameplay(@RequestBody GameRequestDto requestDto) {
        log.info("gameplay: {}", requestDto);
        Game game = gameService.gamePlay(requestDto);
        template.convertAndSend("/sub/games/" + requestDto.getId(), requestDto);
    }
}