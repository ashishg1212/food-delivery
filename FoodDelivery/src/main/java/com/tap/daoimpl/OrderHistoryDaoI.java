package com.tap.daoimpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.tap.dao.OrderHistoryDao;
import com.tap.model.OrderHistory;

public class OrderHistoryDaoI implements OrderHistoryDao {
    private Connection con;

    public OrderHistoryDaoI() {
    	String url = "jdbc:mysql://localhost:3306/tapfoods";
        String username = "root";
        String password = "root";

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
    public void addOrderHistory(OrderHistory orderHistory) {
        String INSERT_QUERY = "INSERT INTO orderhistory (orderHistoryId, orderId, userId) VALUES (?, ?, ?)";
        try {
            PreparedStatement pstmt = con.prepareStatement(INSERT_QUERY);
            pstmt.setInt(1, orderHistory.getOrderHistoryId());
            pstmt.setInt(2, orderHistory.getOrderId());
            pstmt.setInt(3, orderHistory.getUserId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public OrderHistory getOrderHistory(int orderHistoryId) {
        String SELECT_QUERY = "SELECT * FROM orderhistory WHERE orderHistoryId = ?";
        try {
            PreparedStatement pstmt = con.prepareStatement(SELECT_QUERY);
            pstmt.setInt(1, orderHistoryId);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                int orderId = rs.getInt("orderId");
                int userId = rs.getInt("userId");
                return new OrderHistory(orderHistoryId, orderId, userId);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void updateOrderHistory(OrderHistory orderHistory) {
        String UPDATE_QUERY = "UPDATE orderhistory SET orderId = ?, userId = ? WHERE orderHistoryId = ?";
        try {
            PreparedStatement pstmt = con.prepareStatement(UPDATE_QUERY);
            pstmt.setInt(1, orderHistory.getOrderId());
            pstmt.setInt(2, orderHistory.getUserId());
            pstmt.setInt(3, orderHistory.getOrderHistoryId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteOrderHistory(int orderHistoryId) {
        String DELETE_QUERY = "DELETE FROM orderhistory WHERE orderHistoryId = ?";
        try {
            PreparedStatement pstmt = con.prepareStatement(DELETE_QUERY);
            pstmt.setInt(1, orderHistoryId);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<OrderHistory> getAllOrderHistory() {
        List<OrderHistory> orderHistoryList = new ArrayList<>();
        String SELECT_ALL_QUERY = "SELECT * FROM orderhistory";
        try {
            PreparedStatement pstmt = con.prepareStatement(SELECT_ALL_QUERY);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                int orderHistoryId = rs.getInt("orderHistoryId");
                int orderId = rs.getInt("orderId");
                int userId = rs.getInt("userId");
                OrderHistory orderHistory = new OrderHistory(orderHistoryId, orderId, userId);
                orderHistoryList.add(orderHistory);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orderHistoryList;
    }
}
