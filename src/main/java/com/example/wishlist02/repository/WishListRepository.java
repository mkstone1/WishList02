package com.example.wishlist02.repository;

import com.example.wishlist02.Model.Wish;
import com.example.wishlist02.Model.WishList;

import java.sql.*;
import java.util.ArrayList;

public class WishListRepository {
    private WishList activeWishList;
    private ArrayList<WishList> allWishList;

    private final String username = "web@wishlistdbkea22";
    private final String password = "SuperSecret";
    private final String connectionString = "jdbc:mysql://wishlistdbkea22.mysql.database.azure.com:3306/wishlistdb";



    public WishListRepository() {
        allWishList = getAllWishLists();
    }

    public WishList getWishlistByID(int id){
        WishList wishListToReturn = new WishList();
        for(WishList wishlist: allWishList){
            if (wishlist.getWishList_ID() == id){
                return wishlist;
            }
        }
        return wishListToReturn;
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
        String SQL_INSERT = "INSERT INTO wishlist (wishlist_name,user_id) VALUES (?,?)";
        try{
            Connection conn = DriverManager.getConnection(connectionString,username,password);


            PreparedStatement preparedStatement = conn.prepareStatement(SQL_INSERT);
            preparedStatement.setString(1, activeWishList.getName());
            preparedStatement.setString(2, activeWishList.getUserID());


            int row = preparedStatement.executeUpdate();




        }catch(SQLException e){
            System.out.println("Cannot connect to database");
            e.printStackTrace();
        }
    }

    public ArrayList<WishList> getAllWishLists(){
        ArrayList<WishList> allWishLists = new ArrayList<>();
        try{
            Connection conn = DriverManager.getConnection(connectionString,username,password);
            PreparedStatement psts = conn.prepareStatement("SELECT * from wishlist");
            ResultSet resultSet = psts.executeQuery();

            while(resultSet.next()){
                String wishListid = resultSet.getString(1);
                String userID=  resultSet.getString(3);
                allWishLists.add(new WishList(resultSet.getString(2),Integer.parseInt(wishListid), "list?wishListId=" + wishListid + "&userID="+ userID,resultSet.getString(3)));

            }
            this.allWishList = allWishLists;
            return allWishLists;
        }


        catch (SQLException e){
            System.out.println("Cannot connect to database");
            e.printStackTrace();
        }

        return allWishLists;

    }



    public void addToAllWishlists(WishList wishlist){
        allWishList.add(wishlist);
    }

    public ArrayList<WishList> getWishListByUserID(String userID){
        ArrayList<WishList> wishListsByID = new ArrayList<>();
        try{
            Connection conn = DriverManager.getConnection(connectionString,username,password);
            PreparedStatement psts = conn.prepareStatement("SELECT * from wishlist where user_id =" + userID);
            ResultSet resultSet = psts.executeQuery();

            while(resultSet.next()){
                String wishListid = resultSet.getString(1);
                wishListsByID.add(new WishList(resultSet.getString(2),Integer.parseInt(wishListid), "list?wishListId=" + wishListid + "&userID="+ userID,resultSet.getString(3)));
            }
            this.allWishList = wishListsByID;
            return wishListsByID;
        }


        catch (SQLException e){
            System.out.println("Cannot connect to database");
            e.printStackTrace();
        }

        return wishListsByID;

    }

    public void deleteWishListByID(String ID){
        String SQL_DELETE = "DELETE FROM wishlist where wishlist_id= (?)";
        try{
            Connection conn = DriverManager.getConnection(connectionString,username,password);
            PreparedStatement preparedStatement = conn.prepareStatement(SQL_DELETE);
            preparedStatement.setString(1, ID);
            int row = preparedStatement.executeUpdate();
    }
        catch (SQLException e){
            System.out.println("ERROR");
        }


}}
