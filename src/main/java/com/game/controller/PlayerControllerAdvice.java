package com.game.controller;

import com.game.exception.EntityNotFound;
import com.game.exception.ValidationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class PlayerControllerAdvice {
    @ExceptionHandler(ValidationException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public void badRequestException() {
        //nothing
    }

    @ExceptionHandler(EntityNotFound.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public void notFoundException() {
        //nothing
    }
}
