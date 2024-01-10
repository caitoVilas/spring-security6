package com.caito.security01.api.exceptions.controllers;

import com.caito.security01.api.exceptions.customs.NotFoundException;
import com.caito.security01.api.models.responses.ErrorMessage;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.Arrays;

@RestControllerAdvice
@ResponseStatus(HttpStatus.NOT_FOUND)
public class NotFoundExceptionController {
    @ExceptionHandler(NotFoundException.class)
    protected ResponseEntity<ErrorMessage> norFoundHandler(NotFoundException e,
                                                           HttpServletRequest request){
        var response = ErrorMessage.builder()
                .code(HttpStatus.NOT_FOUND.value())
                .status(HttpStatus.NOT_FOUND.name())
                .timestamp(LocalDateTime.now())
                .message(e.getMessage())
                .method(request.getMethod())
                .path(request.getRequestURL().toString())
                .build();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }
}
