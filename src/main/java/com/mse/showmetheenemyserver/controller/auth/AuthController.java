package com.mse.showmetheenemyserver.controller.auth;

import com.mse.showmetheenemyserver.dto.SignUpRequestDto;
import com.mse.showmetheenemyserver.dto.SignUpResponseDto;
import com.mse.showmetheenemyserver.service.auth.AuthService;
import com.mse.showmetheenemyserver.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RequiredArgsConstructor
@RequestMapping("/api/auth")
@RestController
public class AuthController {
    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<SignUpResponseDto> registerUser(@Valid @RequestBody SignUpRequestDto requestDto) {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/auth/register").toUriString());
        return ResponseEntity.created(uri).body(authService.registerUser(requestDto));
    }
}