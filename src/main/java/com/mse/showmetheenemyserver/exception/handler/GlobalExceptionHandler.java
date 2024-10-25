package com.mse.showmetheenemyserver.exception.handler;

import com.mse.showmetheenemyserver.exception.AlreadyExistedUsernameException;
import com.mse.showmetheenemyserver.exception.ErrorDetails;
import com.mse.showmetheenemyserver.exception.GameNotFoundException;
import com.mse.showmetheenemyserver.exception.NotSameTwoPasswordException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(AlreadyExistedUsernameException.class)
    public ResponseEntity<?> alreadyExistedUsernameExceptionHandling(Exception exception, WebRequest request) {
        return new ResponseEntity<>(new ErrorDetails(BAD_REQUEST.value(), exception.getMessage(), request.getDescription(false)), BAD_REQUEST);
    }

    @ExceptionHandler(NotSameTwoPasswordException.class)
    public ResponseEntity<?> notSameTwoPasswordExceptionHandling(Exception exception, WebRequest request) {
        return new ResponseEntity<>(new ErrorDetails(BAD_REQUEST.value(), exception.getMessage(), request.getDescription(false)), BAD_REQUEST);
    }

    @ExceptionHandler(GameNotFoundException.class)
    public ResponseEntity<?> gameNotFoundExceptionHandling(Exception exception, WebRequest request) {
        return new ResponseEntity<>(new ErrorDetails(NOT_FOUND.value(), exception.getMessage(), request.getDescription(false)), NOT_FOUND);
    }
}
