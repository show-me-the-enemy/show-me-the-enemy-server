package com.mse.showmetheenemyserver.exception;

public class AlreadyExistedUsernameException extends RuntimeException {

    private static final String message = "Username already exists";

    public AlreadyExistedUsernameException() {
        super(message);
    }
}
