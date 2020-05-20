package com.rest.usermanagment.controllers;

import com.rest.usermanagment.exceptions.*;
import com.rest.usermanagment.models.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 *  Handles exceptions specifies and returns appropriate return message based on exception
 */
@ControllerAdvice
public  class RestExceptionController {
    
    @ExceptionHandler(value = {UserNotFoundException.class, GroupNotFoundException.class})
     ResponseEntity<ErrorResponse> handleException(NotFoundException exc){
         ErrorResponse error = new ErrorResponse(
                 HttpStatus.NOT_FOUND.value(),
                 exc.getMessage(),
                 System.currentTimeMillis());
         return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = {DuplicateUserException.class, DuplicateGroupException.class})
    ResponseEntity<ErrorResponse> handleException(BadRequestException exc){
        ErrorResponse error = new ErrorResponse(
                HttpStatus.BAD_REQUEST.value(),
                exc.getMessage(),
                System.currentTimeMillis());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
}
