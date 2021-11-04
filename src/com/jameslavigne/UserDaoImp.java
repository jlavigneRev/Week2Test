package com.jameslavigne;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDaoImp implements UserDao {
    Connection connection;

    public UserDaoImp() {
        this.connection = ConnectionFactory.getConnection();
    }

    @Override
    public void addUser(String username, String password, String name, String address) {
        String sql = "INSERT INTO user (username, password, name, address) VALUES (?,?,?,?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            preparedStatement.setString(3, name);
            preparedStatement.setString(4, address);
            int count = preparedStatement.executeUpdate();
            if (count > 0)
                System.out.println("New User Created Successfully");
        } catch (SQLException e) {
            //e.printStackTrace();
            System.out.println(Menu.ANSI_RED + "New User Could Not Be Created." + Menu.ANSI_NORMAL);
        }
    }

    @Override
    public boolean validCredentials(String username, String password) {
        String sql = "SELECT * FROM user WHERE username = ? AND password = ? LIMIT 1";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            ResultSet result = preparedStatement.executeQuery();
            if(result.next()){
                return true;
            }
        } catch (SQLException e) {
            //e.printStackTrace();
        }
        return false;
    }

    @Override
    public User getUserFromUsername(String username) {

        String sql = "SELECT * FROM user WHERE username = ? LIMIT 1";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, username);
            ResultSet result = preparedStatement.executeQuery();
            if(result.next()){
                User user = new User();
                user.setUserId(result.getInt(1));
                user.setUsername(result.getString(2));
                user.setPassword(result.getString(3));
                user.setName(result.getString(4));
                user.setAddress(result.getString(5));
                return user;
            }
        } catch (SQLException e) {
            //e.printStackTrace();
        }
        return null;
    }
}
