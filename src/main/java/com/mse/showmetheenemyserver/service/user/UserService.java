package com.mse.showmetheenemyserver.service.user;

import com.mse.showmetheenemyserver.domain.User;
import com.mse.showmetheenemyserver.dto.LobbyResponseDto;
import com.mse.showmetheenemyserver.dto.UserGameResultRequestDto;
import com.mse.showmetheenemyserver.dto.UserGameResultResponseDto;

import java.util.List;

public interface UserService {
    List<User> getUsers();
    User getUser(String username);
    UserGameResultResponseDto updateUserGameResult(UserGameResultRequestDto requestDto);

    public LobbyResponseDto getLobbyStatus(Long id);
}
