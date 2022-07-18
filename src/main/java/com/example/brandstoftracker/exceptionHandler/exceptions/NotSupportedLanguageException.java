package com.example.brandstoftracker.exceptionHandler.exceptions;
public class NotSupportedLanguageException extends RuntimeException{
    public NotSupportedLanguageException(String message){
        super(message);
    }
}