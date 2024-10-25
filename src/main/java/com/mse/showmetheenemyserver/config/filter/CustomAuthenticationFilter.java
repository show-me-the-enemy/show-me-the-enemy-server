package com.mse.showmetheenemyserver.config.filter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.mse.showmetheenemyserver.dto.LoginResponseDto;
import com.mse.showmetheenemyserver.exception.ErrorDetails;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Slf4j
public class CustomAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private final AuthenticationManager authenticationManager;

    public CustomAuthenticationFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Override // when the user login to app at the first time
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        log.info("Username is: {}", username); log.info("Password is: {}", password);

        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, password);

        return authenticationManager.authenticate(authenticationToken);
    }

    @Override // generate the token and then send that token over to the user
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authentication) throws IOException, ServletException {
        User user = (User)authentication.getPrincipal();

        Algorithm algorithm = Algorithm.HMAC256("secret".getBytes());

        String accessToken = JWT.create()
                .withSubject(user.getUsername()) // identifier
                .withExpiresAt(new Date(System.currentTimeMillis() + 1440 * 60 * 1000))
                .withIssuer(request.getRequestURL().toString())
//                .withClaim("roles", user.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
                .sign(algorithm);

        String refreshToken = JWT.create()
                .withSubject(user.getUsername()) // identifier
                .withExpiresAt(new Date(System.currentTimeMillis() + 30 * 60 * 1000))
                .withIssuer(request.getRequestURL().toString())
                .sign(algorithm);

        log.info("Send token '{}' in http response body to the client", accessToken);

        LoginResponseDto loginResponseDto = LoginResponseDto.builder()
                .statusCode(CREATED.value())
                .message(user.getUsername() + " is logged in correct")
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();

        response.setContentType(APPLICATION_JSON_VALUE);
        response.setStatus(CREATED.value());

        new ObjectMapper().writeValue(response.getOutputStream(), loginResponseDto);
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
        response.setContentType(APPLICATION_JSON_VALUE);
        response.setStatus(BAD_REQUEST.value());

        ErrorDetails error;

        if (failed instanceof BadCredentialsException) {
            log.info("Invalid username or password");
            error = ErrorDetails.builder()
                    .statusCode(BAD_REQUEST.value())
                    .message("Invalid username or password")
                    .details(request.getRequestURI())
                    .build();
        } else {
            error = ErrorDetails.builder()
                    .statusCode(INTERNAL_SERVER_ERROR.value())
                    .message("An unknown error has occurred")
                    .details(request.getRequestURI())
                    .build();
        }
        new ObjectMapper().registerModule(new JavaTimeModule()).writeValue(response.getOutputStream(), error);
    }
}
