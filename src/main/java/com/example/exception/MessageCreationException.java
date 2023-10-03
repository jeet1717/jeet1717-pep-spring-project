package com.example.exception;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class MessageCreationException extends RuntimeException{
    public MessageCreationException(String message) {
        super(message);
    }
}  