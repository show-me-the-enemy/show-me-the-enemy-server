package com.mse.showmetheenemyserver.controller.user;

import com.mse.showmetheenemyserver.domain.User;
import com.mse.showmetheenemyserver.dto.UserGameResultRequestDto;
import com.mse.showmetheenemyserver.dto.UserGameResultResponseDto;
import com.mse.showmetheenemyserver.service.user.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/users")
@RestController
public class UserController {
    private final UserService userService;

    // 회원 전체조회
    @GetMapping()
    public ResponseEntity<List<User>> getUsers() {
        return ResponseEntity.ok(userService.getUsers());
    }

    @PutMapping()
    public ResponseEntity<UserGameResultResponseDto> updateUserGameResult(@RequestBody UserGameResultRequestDto requestDto) {
        log.info("Update {}'s game result", requestDto.getUsername());
        return ResponseEntity.ok(userService.updateUserGameResult(requestDto));
    }
}
