package com.example.wishlist02.repository;


public class Wish {
    private String name;
    private String description;
    private int price;

    public Wish(String name, String description, int pris) {
        this.name = name;
        this.description = description;
        this.price = pris;
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

    @Override
    public String toString() {
        return "Wish{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", pris=" + price +
                '}';
    }
}
