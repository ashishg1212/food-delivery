package com.tap.model;

public class OrderItem {
    private int orderItemId; // Changed from orderId
    private int userId;
    private int menuId;
    private String name;
    private float rating;
    private int quantity;
    private float price;

    public OrderItem() {
    }

    public OrderItem(int orderItemId, int userId, int menuId, String name, float rating, int quantity, float price) {
        this.orderItemId = orderItemId;
        this.userId = userId;
        this.menuId = menuId;
        this.name = name;
        this.rating = rating;
        this.quantity = quantity;
        this.price = price;
    }

    public int getOrderItemId() {
        return orderItemId;
    }

    public void setOrderItemId(int orderItemId) {
        this.orderItemId = orderItemId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getMenuId() {
        return menuId;
    }

    public void setMenuId(int menuId) {
        this.menuId = menuId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }
}
