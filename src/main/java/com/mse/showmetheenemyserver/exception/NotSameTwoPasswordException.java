package com.mse.showmetheenemyserver.exception;

public class NotSameTwoPasswordException extends RuntimeException {

    private static final String message = "The passwords do not match";

    public NotSameTwoPasswordException() {
        super(message);
    }
}
