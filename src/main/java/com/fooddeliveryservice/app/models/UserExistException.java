package com.fooddeliveryservice.app.models;

public class UserExistException{
    private final String message;
    private final int status;

    public UserExistException(String m, int s){
        this.message = m;
        this.status = s;
    }

    public String getMessage() {
        return message;
    }

    public int getStatus() {
        return status;
    }

}
