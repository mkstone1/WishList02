package com.example.wishlist02.repository;

import com.example.wishlist02.Model.User;

import java.sql.*;
import java.util.ArrayList;

public class UserRepository {
    private final String username = "web@wishlistdbkea22";
    private final String password = "SuperSecret";
    private final String connectionString = "jdbc:mysql://wishlistdbkea22.mysql.database.azure.com:3306/wishlistdb";

    private ArrayList<User> allUsers = new ArrayList<>();

    public void createUser(User user) {
        String SQL_INSERT = "INSERT INTO user (user_name, first_name, last_name, email ,phone_number, password) VALUES (?,?,?,?,?,?)";
        try{
            Connection conn = DriverManager.getConnection(connectionString,username,password);


            PreparedStatement preparedStatement = conn.prepareStatement(SQL_INSERT);
            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2,user.getFirstname());
            preparedStatement.setString(3,user.getLastname());
            preparedStatement.setString(4,user.getEmail());
            preparedStatement.setString(5,user.getPhoneNumber());
            preparedStatement.setString(6,user.getPassword());

            int row = preparedStatement.executeUpdate();




        }catch(SQLException e){
            System.out.println("Cannot connect to database");
            e.printStackTrace();
        }
    }

    public ArrayList<User> getAllUsers(){
        return allUsers;
    }

    public ArrayList<User> getAllUsersFromDB(){
        ArrayList<User> allUsers = new ArrayList<>();
        try {
            Connection conn = DriverManager.getConnection(connectionString, username, password);
            PreparedStatement psts = conn.prepareStatement("SELECT * from user");
            ResultSet resultSet = psts.executeQuery();

            while(resultSet.next()){
                User userFromDB = new User(resultSet.getString(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4),resultSet.getString(5),resultSet.getString(6),resultSet.getString(7));
                allUsers.add(userFromDB);
            }
            return allUsers;
        }
        catch (SQLException e){
            System.out.println("Cannot connect to database");
        }

    return allUsers;}
}
