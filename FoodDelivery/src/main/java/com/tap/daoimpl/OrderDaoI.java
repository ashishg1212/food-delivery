package com.tap.daoimpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.tap.dao.OrderDao;
import com.tap.model.ModeOfPayment;
import com.tap.model.Order;
import com.tap.model.OrderStatus;

public class OrderDaoI implements OrderDao {
    private Connection con = null;

    // SQL Queries
    private String INSERT_QUERY = "INSERT INTO `order`(`restaurantId`, `userId`, `totalAmount`, `modeOfPayment`, `status`) VALUES(?,?,?,?,?)";
    private String SELECT_QUERY = "SELECT * FROM `order` WHERE `orderId` = ?";
    private String UPDATE_QUERY = "UPDATE `order` SET `restaurantId` = ?, `userId` = ?, `totalAmount` = ?, `modeOfPayment` = ?, `status` = ? WHERE `orderId` = ?";
    private String DELETE_QUERY = "DELETE FROM `order` WHERE `orderId` = ?";
    private String SELECT_ALL_QUERY = "SELECT * FROM `order`";

    public OrderDaoI() {
    	String url = "jdbc:mysql://localhost:3306/your_database_name";
        String username = your_username;
        String password = your_password;

        try {
        	Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(url, username, password);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addOrder(Order order) {
        PreparedStatement ptstmt = null;
        try {
            ptstmt = con.prepareStatement(INSERT_QUERY);
            ptstmt.setInt(1, order.getRestaurantId());
            ptstmt.setInt(2, order.getUserId());
            ptstmt.setInt(3, order.getTotalAmount());
            ptstmt.setString(4, order.getModeOfPayment().name());
            ptstmt.setString(5, order.getStatus().name());

            ptstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (ptstmt != null) {
                    ptstmt.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public Order getOrder(int orderId) {
        PreparedStatement ptstmt = null;
        ResultSet result = null;
        Order order = null;
        try {
            ptstmt = con.prepareStatement(SELECT_QUERY);
            ptstmt.setInt(1, orderId);
            result = ptstmt.executeQuery();
            if (result.next()) {
                int restaurantId = result.getInt("restaurantId");
                int userId = result.getInt("userId");
                int totalAmount = result.getInt("totalAmount");
                ModeOfPayment modeOfPayment = ModeOfPayment.valueOf(result.getString("modeOfPayment"));
                OrderStatus status = OrderStatus.valueOf(result.getString("status"));
                
                order = new Order(orderId, restaurantId, userId, totalAmount, modeOfPayment, status);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (ptstmt != null) {
                    ptstmt.close();
                }
                if (result != null) {
                    result.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return order;
    }

    @Override
    public void updateOrder(Order order) {
        PreparedStatement ptstmt = null;
        try {
            ptstmt = con.prepareStatement(UPDATE_QUERY);
            ptstmt.setInt(1, order.getRestaurantId());
            ptstmt.setInt(2, order.getUserId());
            ptstmt.setInt(3, order.getTotalAmount());
            ptstmt.setString(4, order.getModeOfPayment().name());
            ptstmt.setString(5, order.getStatus().name());
            ptstmt.setInt(6, order.getOrderId());

            ptstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (ptstmt != null) {
                    ptstmt.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void deleteOrder(int orderId) {
        PreparedStatement ptstmt = null;
        try {
            ptstmt = con.prepareStatement(DELETE_QUERY);
            ptstmt.setInt(1, orderId);
            ptstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (ptstmt != null) {
                    ptstmt.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public List<Order> getAllOrders() {
        PreparedStatement ptstmt = null;
        ResultSet result = null;
        List<Order> orderList = new ArrayList<>();
        try {
            ptstmt = con.prepareStatement(SELECT_ALL_QUERY);
            result = ptstmt.executeQuery();
            while (result.next()) {
                int orderId = result.getInt("orderId");
                int restaurantId = result.getInt("restaurantId");
                int userId = result.getInt("userId");
                int totalAmount = result.getInt("totalAmount");
                ModeOfPayment modeOfPayment = ModeOfPayment.valueOf(result.getString("modeOfPayment"));
                OrderStatus status = OrderStatus.valueOf(result.getString("status"));
                
                Order order = new Order(orderId, restaurantId, userId, totalAmount, modeOfPayment, status);
                orderList.add(order);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (ptstmt != null) {
                    ptstmt.close();
                }
                if (result != null) {
                    result.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return orderList;
    }
}
