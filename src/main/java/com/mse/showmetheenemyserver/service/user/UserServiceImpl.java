package com.mse.showmetheenemyserver.service.user;

import com.mse.showmetheenemyserver.domain.User;
import com.mse.showmetheenemyserver.dto.LobbyResponseDto;
import com.mse.showmetheenemyserver.dto.RankingInfoDto;
import com.mse.showmetheenemyserver.dto.UserGameResultRequestDto;
import com.mse.showmetheenemyserver.dto.UserGameResultResponseDto;
import com.mse.showmetheenemyserver.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.http.HttpStatus.OK;

@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    public List<User> getUsers() {
        log.info("Fetching all users");
        return userRepository.findAll();
    }

    @Override
    public User getUser(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public LobbyResponseDto getLobbyStatus(String username) {
        User user = userRepository.findByUsername(username);
        return LobbyResponseDto.builder()
                .statusCode(OK.value())
                .entity(user)
                .build();
    }

    @Transactional
    public UserGameResultResponseDto updateUserGameResult(UserGameResultRequestDto requestDto) {
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

    @Override
    public List<RankingInfoDto> getTopTenUsers() {
        List<Object[]> usersByNumWinsDesc = userRepository.findUsersByNumWinsDesc();
        List<RankingInfoDto> topTenUsers = new ArrayList<>();

        for (Object[] user : usersByNumWinsDesc) {
            RankingInfoDto dto = RankingInfoDto.builder().user(user).build();
            topTenUsers.add(dto);
            if (topTenUsers.size() == 10) break;
        }

        return topTenUsers;
    }
}