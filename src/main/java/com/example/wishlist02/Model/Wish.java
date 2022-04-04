package com.example.wishlist02.Model;


public class Wish {
    private String name;
    private String description;
    private int price;
    private int wishID;

    public Wish(String name, String description, int pris) {
        this.name = name;
        this.description = description;
        this.price = pris;
    }
    public Wish(String name, String description, int pris, int wishID) {
        this.name = name;
        this.description = description;
        this.price = pris;
        this.wishID = wishID;
    }


    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getPrice() {
        return price;
    }

    public void setWishID(int id){
        this.wishID = id;
    }

    public int getWishID(){
        return wishID;
    }

    @Override
    public String toString() {
        return "Wish{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", pris=" + price +
                '}';
    }
}
