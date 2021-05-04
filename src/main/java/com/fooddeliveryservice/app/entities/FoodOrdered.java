package com.fooddeliveryservice.app.entities;

import javax.persistence.*;
import java.util.List;

@Entity
public class FoodOrdered{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String username;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    private String firstname;
    private String lastname;
    private String contact;
    private String address;
    private String status;
    private String dateTime;
    private int totalPrice;
    @OneToMany(cascade = CascadeType.ALL)
    private List<CartItem> cartItem;

    public FoodOrdered() {
    }

    public FoodOrdered(int id,String username, String firstname, String lastname, String contact, String address, String status, String dateTime, int totalPrice, List<CartItem> cartItem) {
        this.id = id;
        this.username = username;
        this.firstname = firstname;
        this.lastname = lastname;
        this.contact = contact;
        this.address = address;
        this.status = status;
        this.dateTime = dateTime;
        this.totalPrice = totalPrice;
        this.cartItem = cartItem;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }

    public List<CartItem> getCartItem() {
        return cartItem;
    }

    public void setCartItem(List<CartItem> cartItem) {
        this.cartItem = cartItem;
    }
}
