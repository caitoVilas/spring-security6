package com.caito.security01.api.exceptions.controllers;

import com.caito.security01.api.exceptions.customs.InvalidPasswordException;
import com.caito.security01.api.models.responses.ErrorMessage;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
@ResponseStatus
public class InvalidPasswordExceptionController {

    @ExceptionHandler(InvalidPasswordException.class)
    protected ResponseEntity<ErrorMessage> invalidPasswordHandler(InvalidPasswordException e,
                                                                  HttpServletRequest request){
        var response = ErrorMessage.builder()
                .code(HttpStatus.BAD_REQUEST.value())
                .status(HttpStatus.BAD_REQUEST.name())
                .timestamp(LocalDateTime.now())
                .message(e.getMessage())
                .method(request.getMethod())
                .path(request.getRequestURL().toString())
                .build();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }
}
