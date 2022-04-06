package com.example.wishlist02.Model;

import java.io.Serializable;
import java.util.ArrayList;

public class WishList implements Serializable {
    private String name;
    private int wishListID;
    private String url;
    private String userID;

    public WishList(){}


    public WishList(String name, String userID) {
        this.name = name;
        this.userID = userID;
    }


    public WishList(String name, int wishListID, String url,String userID) {
        this.name = name;
        this.wishListID = wishListID;
        this.url = url;
        this.userID = userID;
    }

    public String getName() {
        return name;
    }

    public void generateUrl(){
        url = "list?id=" + wishListID;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setWishList_ID(int wishList_ID){
        this.wishListID = wishList_ID;
    }

    public int getWishList_ID(){
        return wishListID;
    }

    public String getUrl(){
        return url;
    }

    public String getUserID(){
        return userID;
    }


}
