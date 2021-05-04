package com.fooddeliveryservice.app.models;

import java.util.Date;

public class AuthenticationResponse {
    private final String jwt;
    private final Date date;

    public AuthenticationResponse(String jwt,Date date) {
        this.jwt = jwt;
        this.date = date;
    }

    public Date getDate(){
        return this.date;
    }

    public String getEntity() {

        return this.jwt;
    }
}
