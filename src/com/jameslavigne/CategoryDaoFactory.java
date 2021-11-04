package com.jameslavigne;

public class CategoryDaoFactory {
    private static CategoryDao dao = null;

    private CategoryDaoFactory() {
    }

    public static CategoryDao getCategoryDao() {
        if (dao == null)
            dao = new CategoryDaoImp();
        return dao;
    }
}
