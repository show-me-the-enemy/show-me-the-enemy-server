package com.mse.showmetheenemyserver.controller.game;

import com.mse.showmetheenemyserver.domain.GameRoom;
import com.mse.showmetheenemyserver.dto.*;
import com.mse.showmetheenemyserver.service.auth.AuthService;
import com.mse.showmetheenemyserver.service.game.GameService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/game")
public class GameController {

    private final GameService gameService;

    @ResponseBody
    @GetMapping("/create")
    public RoomDto createRoom() {
        return gameService.createRoom();
    }

    @GetMapping("/all")
    public List<GameRoom> findAllRoom() {
        return gameService.findAllRoom();
    }
}