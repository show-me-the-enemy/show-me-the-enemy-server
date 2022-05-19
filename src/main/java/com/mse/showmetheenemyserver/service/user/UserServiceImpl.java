package com.mse.showmetheenemyserver.service.user;

import com.mse.showmetheenemyserver.domain.User;
import com.mse.showmetheenemyserver.dto.UserGameResultRequestDto;
import com.mse.showmetheenemyserver.dto.UserGameResultResponseDto;
import com.mse.showmetheenemyserver.repository.UserRepository;
import com.mse.showmetheenemyserver.service.game.GameService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.springframework.http.HttpStatus.OK;

@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final GameService gameService;

    @Override
    public List<User> getUsers() {
        log.info("Fetching all users");
        return userRepository.findAll();
    }

    @Override
    public User getUser(String username) {
        return userRepository.findByUsername(username);
    }

    @Transactional
    public UserGameResultResponseDto updateUserGameResult(UserGameResultRequestDto requestDto) {
        gameService.findGameById(requestDto.getGameId());

        User user = userRepository.findByUsername(requestDto.getUsername());

        // Only the winner updates the crystal and the number of wins
        user.addNumWins(requestDto.getWon());
        user.addCrystals(requestDto.getCrystal(), requestDto.getWon());

        // The maximum round, whether winner or loser, is updated
        user.updateMaxRound(requestDto.getNumRound());

        return UserGameResultResponseDto.builder()
                .statusCode(OK.value())
                .entity(user)
                .build();
    }
}
