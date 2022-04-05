package com.example.wishlist02.services;

import com.example.wishlist02.Model.User;

import java.util.ArrayList;

public class validateUser {
    private ArrayList<User> allUsers;

    public validateUser(ArrayList<User> allUsers) {
        this.allUsers = allUsers;
    }

    public String checkUser(String username, String password){
        for(User user : allUsers){
            if(username.equals(user.getUsername()) && password.equals(user.getPassword())){
                return user.getUserID();
            }
        }
        return "";
    }
}
