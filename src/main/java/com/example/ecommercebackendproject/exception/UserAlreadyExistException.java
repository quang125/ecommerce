package com.example.ecommercebackendproject.exception;

public class UserAlreadyExistException extends Exception{
    public UserAlreadyExistException() {
        super();
    }

    public UserAlreadyExistException(String message) {
        super(message);
    }
}
