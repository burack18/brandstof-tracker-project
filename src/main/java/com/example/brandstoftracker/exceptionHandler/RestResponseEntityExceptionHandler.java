package com.example.brandstoftracker.exceptionHandler;

import com.example.brandstoftracker.api.httpResponse.ErrorDataResponse;
import com.example.brandstoftracker.api.httpResponse.ErrorResponse;
import com.example.brandstoftracker.exceptionHandler.exceptions.InsufficientException;
import com.example.brandstoftracker.exceptionHandler.exceptions.NotFoundException;
import com.example.brandstoftracker.exceptionHandler.exceptions.NotSupportedLanguageException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class RestResponseEntityExceptionHandler
        extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value
            = { IllegalArgumentException.class, IllegalStateException.class })
    protected ResponseEntity<Object> handleConflict(
            RuntimeException ex, WebRequest request) {
        String bodyOfResponse = "This should be application specific";
        return handleExceptionInternal(ex, bodyOfResponse,
                new HttpHeaders(), HttpStatus.CONFLICT, request);
    }
    @ExceptionHandler(value
            = { NotFoundException.class, NotFoundException.class })
    protected ResponseEntity handleConflictNotFound(
            RuntimeException ex, WebRequest request) {
        return new ResponseEntity(new ErrorResponse(ex.getLocalizedMessage()),HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = { NotSupportedLanguageException.class, NotSupportedLanguageException.class })
    protected ResponseEntity handleConflictNotFoundLanguage(
            RuntimeException ex, WebRequest request) {
        return new ResponseEntity(new ErrorResponse(ex.getLocalizedMessage()),HttpStatus.NOT_FOUND);
    }
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return new ResponseEntity(new ErrorDataResponse("Validation Error",errors),HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(value = { BadCredentialsException.class, BadCredentialsException.class })
    protected ResponseEntity handleConflictBadCredentials(
            RuntimeException ex, WebRequest request) {
        return new ResponseEntity(new ErrorResponse(ex.getMessage()),HttpStatus.UNAUTHORIZED);
    }
    @ExceptionHandler(value = { InsufficientException.class, InsufficientException.class })
    protected ResponseEntity handleConflictInsufficientException(
            RuntimeException ex, WebRequest request) {
        return new ResponseEntity(new ErrorResponse(ex.getMessage()),HttpStatus.BAD_REQUEST);
    }
}