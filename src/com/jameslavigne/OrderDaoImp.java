package com.jameslavigne;

import java.sql.*;

public class OrderDaoImp implements OrderDao{
    Connection connection;

    public OrderDaoImp(){
        connection = ConnectionFactory.getConnection();
    }

    @Override
    public int createOrder(int userId) {
        String sql = "INSERT INTO orders (user_id) VALUES (?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1,userId);
            int count = preparedStatement.executeUpdate();
            if(count > 0){
                System.out.println(Menu.ANSI_CYAN + "Order Confirmed!" + Menu.ANSI_NORMAL);
            }

            try {
                ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
                if(generatedKeys.next()){
                    System.out.println(Menu.ANSI_CYAN + "Processed Order #" + generatedKeys.getInt(1) + Menu.ANSI_NORMAL);
                    return generatedKeys.getInt(1);
                }
            } catch (SQLException e) {
                e.printStackTrace();
                System.out.println("Cannot generate Order Number");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Failed Processing Order.");
        }
        return -1;
    }
}
