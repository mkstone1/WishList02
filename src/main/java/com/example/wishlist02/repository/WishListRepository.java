package com.example.wishlist02.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class WishListRepository {
    private WishList activeWishList;

    public WishListRepository() {
    }

    public WishList getActiveWishList() {
        return activeWishList;
    }

    public void setActiveWishList(WishList activeWishList) {
        this.activeWishList = activeWishList;
    }
    public void saveWishListToDB(int wishIdToAdd) {
        String SQL_INSERT = "INSERT INTO wishlist (listname, wishID) VALUES (?,?)";
        try{
            String username = "web@wishlistdbkea22";
            String password = "SuperSecret";
            String connectionString = "jdbc:mysql://wishlistdbkea22.mysql.database.azure.com:3306/wishlistdb";
            Connection conn = DriverManager.getConnection(connectionString,username,password);



            PreparedStatement preparedStatement = conn.prepareStatement(SQL_INSERT);
            preparedStatement.setString(1, activeWishList.getName());
            preparedStatement.setInt(2,wishIdToAdd);

            int row = preparedStatement.executeUpdate();




        }catch(SQLException e){
            System.out.println("Cannot connect to database");
            e.printStackTrace();
        }
    }
}
