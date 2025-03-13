package com.example.backprojectpapo.exception;

public class UserAlreadyExistsException extends RuntimeException{
    public UserAlreadyExistsException(String message){
        super(message);
    }
}
