package com.jameslavigne;

public class OrderItemDaoFactory {
    private static OrderItemDao dao = null;

    private OrderItemDaoFactory() {
    }

    public static OrderItemDao getOrderItemDao() {
        if (dao == null)
            dao = new OrderItemDaoImp();
        return dao;
    }
}
