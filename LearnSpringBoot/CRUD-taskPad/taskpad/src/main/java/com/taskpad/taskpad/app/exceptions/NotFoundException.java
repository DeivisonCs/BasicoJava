package com.taskpad.taskpad.app.exceptions;

public class NotFoundException extends RuntimeException{
    public NotFoundException(){
        super("Search Not Found!");
    }

    public NotFoundException(String message){
        super(message);
    }
}
