package com.mse.showmetheenemyserver.repository;

import com.mse.showmetheenemyserver.domain.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class UserRepositoryTest {

    @Autowired
    UserRepository userRepository;

    @Test
    public void 유저저장_불러오기() {
        //given
        String username = "gwangjin";
        String password = "12345678";

        User user = User.builder()
                .username(username)
                .password(password)
                .build();

        //when
        userRepository.save(user);
        List<User> users = userRepository.findAll();

        //then
        assertThat(users.get(0).getUsername()).isEqualTo(username);
        assertThat(users.get(0).getPassword()).isEqualTo(password);
    }
}