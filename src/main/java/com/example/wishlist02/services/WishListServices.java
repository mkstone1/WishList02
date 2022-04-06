package com.example.wishlist02.services;

import com.example.wishlist02.Model.WishList;

import java.util.ArrayList;

public class WishListServices {


    public boolean doesWishListExist(String listName, ArrayList<WishList> allWishLists){
        for (WishList lists : allWishLists){
            if(listName.equals(lists.getName())){
                return true;
            }
        }
        return false;
    }
}
