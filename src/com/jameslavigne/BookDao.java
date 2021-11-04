package com.jameslavigne;

import java.util.List;

public interface BookDao {

    List<Book> getBooksByCategory(int id);
}
