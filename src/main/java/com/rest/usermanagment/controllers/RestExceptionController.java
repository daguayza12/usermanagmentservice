package com.rest.usermanagment.controllers;

import com.rest.usermanagment.exceptions.DuplicateUserException;
import com.rest.usermanagment.exceptions.DuplicateUserGroupException;
import com.rest.usermanagment.objects.ErrorResponse;
import com.rest.usermanagment.exceptions.UserGroupNotFoundException;
import com.rest.usermanagment.exceptions.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public  class RestExceptionController {
    
    @ExceptionHandler(value = {UserNotFoundException.class, UserGroupNotFoundException.class})
     ResponseEntity<ErrorResponse> handleException(RuntimeException exc){
         ErrorResponse error = new ErrorResponse(
                 HttpStatus.NOT_FOUND.value(),
                 exc.getMessage(),
                 System.currentTimeMillis());
         return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = {DuplicateUserException.class, DuplicateUserGroupException.class})
    ResponseEntity<ErrorResponse> handleException(Exception exc){
        ErrorResponse error = new ErrorResponse(
                HttpStatus.BAD_REQUEST.value(),
                exc.getMessage(),
                System.currentTimeMillis());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
}
