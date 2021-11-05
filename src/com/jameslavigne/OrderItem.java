package com.jameslavigne;

public class OrderItem {
    private int orderId;
    private int bookId;
    private int quantity;
    private double price;
    private String title;

    public OrderItem(){
    }

    public OrderItem(int orderId, int bookId, int quantity, double price, String title) {
        this.orderId = orderId;
        this.bookId = bookId;
        this.quantity = quantity;
        this.price = price;
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void incrementQuantity(){
        this.quantity++;
    }

    public double getPrice() {
        return price;
    }

    public void addToPrice(double price){
        this.price += price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "OrderItem{" +
                "bookId=" + bookId +
                ", quantity=" + quantity +
                ", price=" + price +
                '}';
    }
}
