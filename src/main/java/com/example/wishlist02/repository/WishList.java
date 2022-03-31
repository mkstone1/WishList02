package com.example.wishlist02.repository;

import java.io.Serializable;
import java.util.ArrayList;

public class WishList implements Serializable {
    private String name;
    private int wishList_ID;
    private String url;

    public WishList(){}


    public WishList(String name) {
        this.name = name;
    }
    public WishList(String name, int wishList_ID, String url) {
        this.name = name;
        this.wishList_ID = wishList_ID;
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void generateUrl(){
        url = "list?id=" + wishList_ID;
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

    public String getUrl(){
        return url;
    }


}
