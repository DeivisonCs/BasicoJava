package com.taskpad.taskpad.app.exceptionsHandler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.taskpad.taskpad.app.exceptions.MissingArgsException;
import com.taskpad.taskpad.app.exceptions.NotFoundException;

@ControllerAdvice
public class RestExceptionsHandler extends ResponseEntityExceptionHandler{
 
    @ExceptionHandler(MissingArgsException.class)
    private ResponseEntity<ErrorMessage> missingFielErrordHandler(MissingArgsException exception){
        ErrorMessage response = new ErrorMessage(HttpStatus.BAD_REQUEST, exception.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @ExceptionHandler(NotFoundException.class)
    private ResponseEntity<ErrorMessage> notFoundErrorHandler(NotFoundException exception){
        ErrorMessage response = new ErrorMessage(HttpStatus.NOT_FOUND, exception.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }
    
    @ExceptionHandler(RuntimeException.class)
    private ResponseEntity<ErrorMessage> runTimeErrorHandler(RuntimeException exception){
        ErrorMessage response = new ErrorMessage(HttpStatus.INTERNAL_SERVER_ERROR, exception.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }
}
