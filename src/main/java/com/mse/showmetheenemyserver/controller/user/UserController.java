package com.mse.showmetheenemyserver.controller.user;

import com.mse.showmetheenemyserver.domain.User;
import com.mse.showmetheenemyserver.dto.LobbyResponseDto;
import com.mse.showmetheenemyserver.dto.RankingInfoDto;
import com.mse.showmetheenemyserver.dto.UserGameResultRequestDto;
import com.mse.showmetheenemyserver.dto.UserGameResultResponseDto;
import com.mse.showmetheenemyserver.service.user.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/users")
@RestController
public class UserController {
    private final UserService userService;

    @GetMapping()
    public ResponseEntity<List<User>> getUsers() {
        return ResponseEntity.ok(userService.getUsers());
    }

    @PutMapping()
    public ResponseEntity<UserGameResultResponseDto> updateUserGameResult(@RequestBody UserGameResultRequestDto requestDto) {
        log.info("Update {}'s game result", requestDto.getUsername());
        return ResponseEntity.ok(userService.updateUserGameResult(requestDto));
    }

    @GetMapping("/lobby/{username}")
    public ResponseEntity<LobbyResponseDto> getUserLobbyProperty(@PathVariable String username) {
        log.info("Get {}'s game stats like crystals, numWins", username);
        return ResponseEntity.ok(userService.getLobbyStatus(username));
    }

    @GetMapping("/rankings")
    public ResponseEntity<List<RankingInfoDto>> getUsersByRanking() {
        log.info("Get top 10 users ranking");
        return ResponseEntity.ok(userService.getTopTenUsers());
    }
}
