package com.example.backprojectpapo.exception;

public class InvalidVerificationCodeException extends RuntimeException{
    public InvalidVerificationCodeException(String message){
        super(message);
    }
}
