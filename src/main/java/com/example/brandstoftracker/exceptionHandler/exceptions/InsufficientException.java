package com.example.brandstoftracker.exceptionHandler.exceptions;

public class InsufficientException extends RuntimeException{
    public InsufficientException(String message){
        super(message);
    }
}
