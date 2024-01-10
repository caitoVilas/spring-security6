package com.caito.security01.api.exceptions.customs;

public class InvalidPasswordException extends RuntimeException{
    public InvalidPasswordException(String message) {
        super(message);
    }
}
