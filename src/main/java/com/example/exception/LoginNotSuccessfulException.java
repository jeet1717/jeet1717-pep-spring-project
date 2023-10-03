package com.example.exception;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class LoginNotSuccessfulException extends RuntimeException{
    public LoginNotSuccessfulException(String message) {
        super(message);
    }
}  