package com.mse.showmetheenemyserver.exception.handler;

import com.mse.showmetheenemyserver.exception.AlreadyExistedUsernameException;
import com.mse.showmetheenemyserver.exception.ErrorDetails;
import com.mse.showmetheenemyserver.exception.NotSameTwoPasswordException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(AlreadyExistedUsernameException.class)
    public ResponseEntity<?> alreadyExistedUsernameExceptionHandling(Exception exception, WebRequest request) {
        return new ResponseEntity<>(new ErrorDetails(LocalDateTime.now(), exception.getMessage(), request.getDescription(false)), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NotSameTwoPasswordException.class)
    public ResponseEntity<?> notSameTwoPasswordExceptionHandling(Exception exception, WebRequest request) {
        return new ResponseEntity<>(new ErrorDetails(LocalDateTime.now(), exception.getMessage(), request.getDescription(false)), HttpStatus.BAD_REQUEST);
    }
}
