package com.example.wishlist02.services;

import com.example.wishlist02.Model.User;

import java.util.ArrayList;

public class validateUser {
    private ArrayList<User> alleUsers;
    private String usernameToValidate;
    private String passwordToValidate;

    public validateUser(ArrayList<User> alleUsers, String usernameToValidate, String passwordToValidate) {
        this.alleUsers = alleUsers;
        this.usernameToValidate = usernameToValidate;
        this.passwordToValidate = passwordToValidate;
    }
}
