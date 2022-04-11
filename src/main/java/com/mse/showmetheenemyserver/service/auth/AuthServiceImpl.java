package com.mse.showmetheenemyserver.service.auth;

import com.mse.showmetheenemyserver.domain.User;
import com.mse.showmetheenemyserver.dto.SignUpRequestDto;
import com.mse.showmetheenemyserver.dto.SignUpResponseDto;
import com.mse.showmetheenemyserver.exception.AlreadyExistedUsernameException;
import com.mse.showmetheenemyserver.exception.NotSameTwoPasswordException;
import com.mse.showmetheenemyserver.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;

@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class AuthServiceImpl implements AuthService, UserDetailsService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);

        if (user == null) {
            log.error("User not found in the database");
            throw new UsernameNotFoundException("User not found in the database");
        } else {
            log.info("User found in the database: {}", username);
        }

        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();

        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), authorities);
    }

    @Override
    @Transactional
    public SignUpResponseDto registerUser(SignUpRequestDto requestDto) {
        if (userRepository.existsByUsername(requestDto.getUsername())) {
            throw new AlreadyExistedUsernameException();
        } else if (requestDto.isDifferentTwoPassword()) {
            throw new NotSameTwoPasswordException();
        }

        log.info("Saving new user {} to the database", requestDto.getUsername());

        User newUser = userRepository.save(User
                .builder()
                .username(requestDto.getUsername())
                .password(passwordEncoder.encode(requestDto.getPassword()))
                .build()
        );

        return new SignUpResponseDto(newUser);
    }
}
