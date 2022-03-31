package com.example.wishlist02.repository;

import java.sql.*;
import java.util.ArrayList;

public class WishListRepository {
    private WishList activeWishList;

    private final String username = "web@wishlistdbkea22";
    private final String password = "SuperSecret";
    private final String connectionString = "jdbc:mysql://wishlistdbkea22.mysql.database.azure.com:3306/wishlistdb";



    public WishListRepository() {
    }

    public WishList getActiveWishList() {
        return activeWishList;
    }

    public int getLastCreatedWishListID(){
        //Til at oprette forbindelse til DB
        try {
            Connection conn = DriverManager.getConnection(connectionString, username, password);
            PreparedStatement psts = conn.prepareStatement("SELECT wishlist_id from wishlist order by wishlist_id DESC LIMIT 1");
            ResultSet resultSet = psts.executeQuery();
            int wishListID = 0;
            while(resultSet.next()){
                wishListID = Integer.parseInt(resultSet.getString(1));
            }
            return wishListID;
        }

        catch (SQLException e){
            System.out.println("Cannot connect to database or error in database");
            e.printStackTrace();
            return -1;
        }
    }

    public void setActiveWishList(WishList activeWishList) {
        this.activeWishList = activeWishList;
    }
    public void saveWishListToDB() {
        String SQL_INSERT = "INSERT INTO wishlist (wishlist_name) VALUES (?)";
        try{
            Connection conn = DriverManager.getConnection(connectionString,username,password);


            PreparedStatement preparedStatement = conn.prepareStatement(SQL_INSERT);
            preparedStatement.setString(1, activeWishList.getName());

            int row = preparedStatement.executeUpdate();




        }catch(SQLException e){
            System.out.println("Cannot connect to database");
            e.printStackTrace();
        }
    }

    public ArrayList<String> getAllWishLists(){
        ArrayList<String> allWishLists = new ArrayList<>();
        try{
            Connection conn = DriverManager.getConnection(connectionString,username,password);
            PreparedStatement psts = conn.prepareStatement("SELECT wishlist_name from wishlist");
            ResultSet resultSet = psts.executeQuery();

            while(resultSet.next()){
                allWishLists.add(resultSet.getString(1));
            }
            return allWishLists;
        }


        catch (SQLException e){

        }

        return allWishLists;

    }
}
