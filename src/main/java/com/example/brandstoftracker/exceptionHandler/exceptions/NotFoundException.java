package com.example.brandstoftracker.exceptionHandler.exceptions;

public class NotFoundException extends RuntimeException{
    public NotFoundException(String message){
        super(message);
    }
}
