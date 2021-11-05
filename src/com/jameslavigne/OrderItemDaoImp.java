package com.jameslavigne;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class OrderItemDaoImp implements OrderItemDao{
    Connection connection = null;

    public OrderItemDaoImp(){
        connection = ConnectionFactory.getConnection();
    }

    @Override
    public void addOrderItem(OrderItem oi) {
        String sql = "INSERT INTO order_item (order_id, book_id, quantity, price) VALUES (?, ?, ?, ?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, oi.getOrderId());
            preparedStatement.setInt(2, oi.getBookId());
            preparedStatement.setInt(3, oi.getQuantity());
            preparedStatement.setDouble(4, oi.getPrice());
            int count = preparedStatement.executeUpdate();
            if(count == 0)
                System.out.println("Could Not Add Order Item");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
