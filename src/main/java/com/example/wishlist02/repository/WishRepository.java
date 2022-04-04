package com.example.wishlist02.repository;

import com.example.wishlist02.Model.Wish;

import java.sql.*;
import java.util.ArrayList;

public class WishRepository {
    private final String username = "web@wishlistdbkea22";
    private final String password = "SuperSecret";
    private final String connectionString = "jdbc:mysql://wishlistdbkea22.mysql.database.azure.com:3306/wishlistdb";

    public int saveWishToDB(Wish wishToSave, int wishListID) {
        String SQL_INSERT = "INSERT INTO wish (wish_name, description, price, wishlist_id) VALUES (?,?,?,?)";
        try {
            //Til at oprette forbindelse til DB
            Connection conn = DriverManager.getConnection(connectionString, username, password);

            //Data til at lave wish
            String wishName = wishToSave.getName();
            String wishDesc = wishToSave.getDescription();
            int wishPrice = wishToSave.getPrice();

            //laver SQL statement
            PreparedStatement preparedStatement = conn.prepareStatement(SQL_INSERT);
            preparedStatement.setString(1, wishName);
            preparedStatement.setString(2, wishDesc);
            preparedStatement.setInt(3, wishPrice);
            preparedStatement.setInt(4, wishListID);

            int row = preparedStatement.executeUpdate();

            //Finder det wishID der lige er blevet oprettet og returnere det
            PreparedStatement psts = conn.prepareStatement("SELECT wishID from wish order by wishID DESC LIMIT 1");
            ResultSet resultSet = psts.executeQuery();
            while(resultSet.next()){
                row = Integer.parseInt(resultSet.getString(1));
            }
            return row;


        } catch (SQLException e) {
            System.out.println("Cannot connect to database or error in db");
            e.printStackTrace();
        }
        return -1;
    }

    public ArrayList<Wish> getWishesFromWishlistID(String id){
        ArrayList<Wish> allWishes = new ArrayList<>();
        try{

            Connection conn = DriverManager.getConnection(connectionString,username,password);
            PreparedStatement psts = conn.prepareStatement("select wish_name,description,price,wishID from wish where wishlist_id =" +id);
//select wish_name,description,price from wishlist left join wish on wishlist.wishlist_id = wish.wishlist_id\n" +
//                    "where wishlist.wishlist_id =" +id

            ResultSet resultSet = psts.executeQuery();

            while(resultSet.next()){
                String wish_name = resultSet.getString(1);
                String description = resultSet.getString(2);
                int price = resultSet.getInt(3);
                int wishID = resultSet.getInt(4);
                allWishes.add(new Wish(wish_name,description,price,wishID));
            }
            return allWishes;

        } catch (SQLException e) {
            System.out.println("Cannot connect to database");
            e.printStackTrace();
        }
        return allWishes;
    }
}
