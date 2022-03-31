package com.example.wishlist02.repository;

import java.util.ArrayList;

public class WishList {
    private String name;
    private int wishList_ID;

    public WishList(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setWishList_ID(int wishList_ID){
        this.wishList_ID = wishList_ID;
    }

    public int getWishList_ID(){
        return wishList_ID;
    }


}
