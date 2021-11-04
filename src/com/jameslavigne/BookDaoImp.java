package com.jameslavigne;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookDaoImp implements BookDao{
    Connection connection;

    public BookDaoImp() {
        this.connection = ConnectionFactory.getConnection();
    }

    @Override
    public List<Book> getBooksByCategory(int id) {
        List<Book> books = new ArrayList<>();
        String sql = "SELECT * FROM book WHERE cat_id = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            ResultSet results = preparedStatement.executeQuery();
            while(results.next()){
                Book book = new Book();
                book.setBookId(results.getInt(1));
                book.setTitle(results.getString(2));
                book.setAuthor(results.getString(3));
                book.setIsbn(results.getString(4));
                book.setCatId(results.getInt(5));
                book.setPrice(results.getDouble(6));
                books.add(book);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return books;
    }
}
