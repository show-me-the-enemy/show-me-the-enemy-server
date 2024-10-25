package com.mse.showmetheenemyserver.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "game_id")
    private Long id;

    private String firstUsername;

    private String secondUsername;
    @Enumerated(value = EnumType.STRING)
    private GameStatus status;

    @Builder
    public Game(String firstUsername, String secondUsername, GameStatus status) {
        this.firstUsername = firstUsername;
        this.secondUsername = secondUsername;
        this.status = status;
    }

    public void connectSecondUser(String username) {
        this.secondUsername = username;
    }

    public void changeGameStatus(GameStatus status) {
        this.status = status;
    }
}