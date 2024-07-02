package com.tap.daoimpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.tap.dao.OrderItemDao;
import com.tap.model.OrderItem;

public class OrderItemDaoI implements OrderItemDao {
    private Connection con = null;

    // SQL Queries
    private String INSERT_QUERY = "INSERT INTO `orderitems`(`orderItemId`, `userId`, `menuId`, `name`, `rating`, `quantity`, `price`) VALUES(?,?,?,?,?,?,?)";
    private String SELECT_QUERY = "SELECT * FROM `orderitems` WHERE `orderItemId` = ?";
    private String UPDATE_QUERY = "UPDATE `orderitems` SET `name` = ?, `rating` = ?, `quantity` = ?, `price` = ? WHERE `orderItemId` = ?";
    private String DELETE_QUERY = "DELETE FROM `orderitems` WHERE `orderItemId` = ?";
    private String SELECT_ALL_QUERY = "SELECT * FROM `orderitems`";

    public OrderItemDaoI(Connection con) {
    	String url = "jdbc:mysql://localhost:3306/your_databse_name";
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
    public void addOrderItem(OrderItem orderItem) {
        PreparedStatement ptstmt = null;
        try {
            ptstmt = con.prepareStatement(INSERT_QUERY);
            ptstmt.setInt(1, orderItem.getOrderItemId());
            ptstmt.setInt(2, orderItem.getUserId());
            ptstmt.setInt(3, orderItem.getMenuId());
            ptstmt.setString(4, orderItem.getName());
            ptstmt.setFloat(5, orderItem.getRating());
            ptstmt.setInt(6, orderItem.getQuantity());
            ptstmt.setFloat(7, orderItem.getPrice());

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
    public OrderItem getOrderItem(int orderItemId) {
        PreparedStatement ptstmt = null;
        ResultSet result = null;
        OrderItem orderItem = null;
        try {
            ptstmt = con.prepareStatement(SELECT_QUERY);
            ptstmt.setInt(1, orderItemId);
            result = ptstmt.executeQuery();
            if (result.next()) {
                int userId = result.getInt("userId");
                int menuId = result.getInt("menuId");
                String name = result.getString("name");
                float rating = result.getFloat("rating");
                int quantity = result.getInt("quantity");
                float price = result.getFloat("price");

                orderItem = new OrderItem(orderItemId, userId, menuId, name, rating, quantity, price);
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
        return orderItem;
    }

    @Override
    public void updateOrderItem(OrderItem orderItem) {
        PreparedStatement ptstmt = null;
        try {
            ptstmt = con.prepareStatement(UPDATE_QUERY);
            ptstmt.setString(1, orderItem.getName());
            ptstmt.setFloat(2, orderItem.getRating());
            ptstmt.setInt(3, orderItem.getQuantity());
            ptstmt.setFloat(4, orderItem.getPrice());
            ptstmt.setInt(5, orderItem.getOrderItemId());

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
    public void deleteOrderItem(int orderItemId) {
        PreparedStatement ptstmt = null;
        try {
            ptstmt = con.prepareStatement(DELETE_QUERY);
            ptstmt.setInt(1, orderItemId);
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
    public List<OrderItem> getAllOrderItems() {
        PreparedStatement ptstmt = null;
        ResultSet result = null;
        List<OrderItem> orderItemList = new ArrayList<>();
        try {
            ptstmt = con.prepareStatement(SELECT_ALL_QUERY);
            result = ptstmt.executeQuery();
            while (result.next()) {
                int orderItemId = result.getInt("orderItemId");
                int userId = result.getInt("userId");
                int menuId = result.getInt("menuId");
                String name = result.getString("name");
                float rating = result.getFloat("rating");
                int quantity = result.getInt("quantity");
                float price = result.getFloat("price");

                OrderItem orderItem = new OrderItem(orderItemId, userId, menuId, name, rating, quantity, price);
                orderItemList.add(orderItem);
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
        return orderItemList;
    }
}
