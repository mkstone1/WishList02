package com.example.wishlist02.repository;

import java.sql.*;

public class WishRepository {

    public int saveWishToDB(Wish wishToSave) {
        String SQL_INSERT = "INSERT INTO wish (NAME, description, price) VALUES (?,?,?)";
        try {
            //Til at oprette forbindelse til DB
            String username = "web@wishlistdbkea22";
            String password = "SuperSecret";
            String connectionString = "jdbc:mysql://wishlistdbkea22.mysql.database.azure.com:3306/wishlistdb";
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

            int row = preparedStatement.executeUpdate();

            //Finder det wishID der lige er blevet oprettet og returnere det
            PreparedStatement psts = conn.prepareStatement("SELECT wishID from wish order by wishID DESC LIMIT 1");
            ResultSet resultSet = psts.executeQuery();
            while(resultSet.next()){
                row = Integer.parseInt(resultSet.getString(1));
            }
            return row;


        } catch (SQLException e) {
            System.out.println("Cannot connect to database");
            e.printStackTrace();
        }
        return -1;
    }
}
