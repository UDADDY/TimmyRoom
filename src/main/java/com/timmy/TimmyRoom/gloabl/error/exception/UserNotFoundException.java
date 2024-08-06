package com.timmy.TimmyRoom.gloabl.error.exception;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String message){
        super(message);
    }
}
