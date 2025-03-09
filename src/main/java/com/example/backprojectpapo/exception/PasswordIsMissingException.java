package com.example.backprojectpapo.exception;


public class PasswordIsMissingException extends RuntimeException{
    public PasswordIsMissingException(String message){
        super(message);
    }
}
