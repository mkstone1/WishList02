package com.example.wishlist02.repository;

import java.util.ArrayList;

public class WishList {
    private String name;
    private ArrayList<Wish> wishList;

    public WishList(String name) {
        this.name = name;
        this.wishList = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Wish> getWishList() {
        return wishList;
    }

    public void setWishList(ArrayList<Wish> wishList) {
        this.wishList = wishList;
    }

    public void addWish(Wish wishToAdd) {
        wishList.add(wishToAdd);
    }
}
