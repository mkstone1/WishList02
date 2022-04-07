package com.example.wishlist02.services;

import com.example.wishlist02.Model.User;

import java.util.ArrayList;
import java.util.Locale;

public class UserServices {

    public String checkUser(String username, String password, ArrayList<User> allUsers){
        for(User user : allUsers){
            if(username.toLowerCase().equals(user.getUsername().toLowerCase()) && password.equals(user.getPassword())){
                return user.getUserID();
            }
        }
        return "";
    }


}
