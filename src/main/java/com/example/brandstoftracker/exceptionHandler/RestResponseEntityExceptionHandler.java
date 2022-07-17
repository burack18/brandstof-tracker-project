package com.example.brandstoftracker.exceptionHandler;

import com.example.brandstoftracker.api.httpResponse.ErrorResponse;
import com.example.brandstoftracker.exceptionHandler.exceptions.NotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

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
}