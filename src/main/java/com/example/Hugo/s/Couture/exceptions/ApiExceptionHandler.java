package com.example.Hugo.s.Couture.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(value = {InvalidRequestException.class})
    public ResponseEntity<Object> handleApiRequestException(InvalidRequestException e, WebRequest request) {
        ApiException apiException = new ApiException(
                new Date(),
                e.getMessage(),
                request.getDescription(false));
        return new ResponseEntity<>(apiException, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = {InvalidUserException.class})
    public ResponseEntity<Object> handleApiRequestException(InvalidUserException e, WebRequest request) {
        ApiException apiException = new ApiException(
                new Date(),
                e.getMessage(),
                request.getDescription(false));
        return new ResponseEntity<>(apiException, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = {UserRegistrationException.class})
    public ResponseEntity<Object> handleApiRequestException(UserRegistrationException e, WebRequest request) {
        ApiException apiException = new ApiException(
                new Date(),
                e.getMessage(),
                request.getDescription(false));
        return new ResponseEntity<>(apiException, HttpStatus.NOT_FOUND);
    }
}
