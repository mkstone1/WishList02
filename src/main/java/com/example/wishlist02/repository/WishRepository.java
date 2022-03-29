package com.example.wishlist02.repository;

import java.sql.*;

public class WishRepository {

    public void saveWishToDB(Wish wishToSave) {
        String SQL_INSERT = "INSERT INTO wish (NAME, description, price) VALUES (?,?,?)";
        try{
            String username = "web@wishlistdbkea22";
            String password = "SuperSecret";
            String connectionString = "jdbc:mysql://wishlistdbkea22.mysql.database.azure.com:3306/wishlistdb";
            Connection conn = DriverManager.getConnection(connectionString,username,password);

            String wishName = wishToSave.getName();
            String wishDesc =wishToSave.getDescription();
            int wishPrice = wishToSave.getPrice();

            PreparedStatement preparedStatement = conn.prepareStatement(SQL_INSERT);
            preparedStatement.setString(1,wishName);
            preparedStatement.setString(2,wishDesc);
            preparedStatement.setInt(3,wishPrice);

            int row = preparedStatement.executeUpdate();

            System.out.println(row);


        }catch(SQLException e){
            System.out.println("Cannot connect to database");
            e.printStackTrace();
        }
    }
}
