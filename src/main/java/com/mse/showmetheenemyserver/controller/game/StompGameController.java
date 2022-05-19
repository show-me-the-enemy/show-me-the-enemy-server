package com.mse.showmetheenemyserver.controller.game;

import com.mse.showmetheenemyserver.domain.Game;
import com.mse.showmetheenemyserver.domain.GameStatus;
import com.mse.showmetheenemyserver.dto.BuildUpRequestDto;
import com.mse.showmetheenemyserver.dto.BuildUpResponseDto;
import com.mse.showmetheenemyserver.exception.GameNotFoundException;
import com.mse.showmetheenemyserver.repository.GameRepository;
import com.mse.showmetheenemyserver.service.game.GameService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@RestController
public class StompGameController {

    private final SimpMessagingTemplate template;
    private final GameRepository gameRepository;

    private final GameService gameService;

    @MessageMapping("/build-up")
    public void buildUp(@RequestBody BuildUpRequestDto requestDto) {
        log.info("Game room number '{}' '{}' killed '{}' monsters and chose item '{}'",
                requestDto.getId(),
                requestDto.getSender(),
                requestDto.getNumMonsters(),
                requestDto.getNumItem()
        );
        Optional<Game> game = gameRepository.findById(requestDto.getId());
        BuildUpResponseDto buildUpResponseDto = BuildUpResponseDto.builder()
                .sender(requestDto.getSender())
                .status(game.orElseThrow(GameNotFoundException::new).getStatus())
                .numMonsters(requestDto.getNumMonsters())
                .numItem(requestDto.getNumItem())
                .build();
        template.convertAndSend("/sub/games/" + requestDto.getId(), buildUpResponseDto, gameService.headers("buildup"));
    }
}
