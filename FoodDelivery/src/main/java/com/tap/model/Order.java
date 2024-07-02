package com.tap.model;

public class Order {
    private int orderId;
    private int restaurantId;
    private int userId;
    private int totalAmount;
    private ModeOfPayment modeOfPayment;
    private OrderStatus status;
    
    public Order() {
        
    }

    public Order(int orderId, int restaurantId, int userId, int totalAmount, ModeOfPayment modeOfPayment, OrderStatus status) {
        super();
        this.orderId = orderId;
        this.restaurantId = restaurantId;
        this.userId = userId;
        this.totalAmount = totalAmount;
        this.modeOfPayment = modeOfPayment;
        this.status = status;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(int restaurantId) {
        this.restaurantId = restaurantId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(int totalAmount) {
        this.totalAmount = totalAmount;
    }

    public ModeOfPayment getModeOfPayment() {
        return modeOfPayment;
    }

    public void setModeOfPayment(ModeOfPayment modeOfPayment) {
        this.modeOfPayment = modeOfPayment;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Order [orderId=" + orderId + ", restaurantId=" + restaurantId + ", userId=" + userId
                + ", totalAmount=" + totalAmount + ", modeOfPayment=" + modeOfPayment + ", status=" + status + "]";
    }
}
