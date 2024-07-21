package com.taskpad.taskpad.app.exceptionsHandler;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;


@AllArgsConstructor
@Getter
@Setter
public class ErrorMessage {
    private HttpStatus status;
    private String message;
}
