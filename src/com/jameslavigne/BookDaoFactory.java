package com.jameslavigne;

public class BookDaoFactory {
    private static BookDao dao = null;

    private BookDaoFactory() {
    }

    public static BookDao getBookDao() {
        if (dao == null)
            dao = new BookDaoImp();
        return dao;
    }
}
