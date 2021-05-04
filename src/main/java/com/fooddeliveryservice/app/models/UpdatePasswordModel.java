package com.fooddeliveryservice.app.models;

public class UpdatePasswordModel{
    private String username;
    private String contact;
    private String password;

    public UpdatePasswordModel(String username, String contact, String password) {
        this.username = username;
        this.contact = contact;
        this.password = password;
    }

    public UpdatePasswordModel() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
