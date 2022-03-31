package com.example.wishlist02.Model;

public class User {
    private String username;
    private String firstname;
    private String lastname;
    private String email;
    private String phoneNumber;
    private String password;

    public User(String username , String password, String firstname, String lastname, String email, String phoneNumber) {
        this.username = username;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.password = password;
    }
}
