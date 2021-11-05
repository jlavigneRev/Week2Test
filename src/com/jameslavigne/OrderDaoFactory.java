package com.jameslavigne;

public class OrderDaoFactory {
    private static OrderDao dao = null;

    private OrderDaoFactory() {
    }

    public static OrderDao getOrderDao() {
        if (dao == null)
            dao = new OrderDaoImp();
        return dao;
    }
}
