package com.taskpad.taskpad.app.exceptions;

public class MissingArgsException extends RuntimeException{
    public MissingArgsException (){
        super("Missing Field Request!");
    }    

    public MissingArgsException(String message){
        super(message);
    }
}
