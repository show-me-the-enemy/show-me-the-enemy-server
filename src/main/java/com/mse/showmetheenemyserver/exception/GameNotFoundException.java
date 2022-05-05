package com.mse.showmetheenemyserver.exception;

public class GameNotFoundException extends RuntimeException {

    private static final String message = "Game not found";
    public GameNotFoundException() {
        super(message);
    }
}
