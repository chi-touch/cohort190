package com.africa.semicolon.devops.exceptions;

public class ThisUserAlreadyExistException extends RuntimeException{
    public ThisUserAlreadyExistException(String message){
        super(message);
    }
}
