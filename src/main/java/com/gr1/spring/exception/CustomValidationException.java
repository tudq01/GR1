package com.gr1.spring.exception;
public class CustomValidationException extends RuntimeException{
    public CustomValidationException(String message){
        super(message);
    }
}
