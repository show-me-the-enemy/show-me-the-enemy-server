package com.mse.showmetheenemyserver.exception;

import lombok.Builder;
import lombok.Data;

@Data
public class ErrorDetails {
    private int statusCode;
    private String message;
    private String details;

    @Builder
    public ErrorDetails(int statusCode, String message, String details) {
        this.statusCode = statusCode;
        this.message = message;
        this.details = details;
    }
}
