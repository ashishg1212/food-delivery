package com.tap.daoimpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.tap.dao.RestaurantDao;
import com.tap.model.Restaurant;

public class RestaurantDaoI implements RestaurantDao {
    private Connection con = null;

    // SQL Queries
    private String INSERT_QUERY = "INSERT INTO `restaurant`(`name`, `imagePath`, `rating`, `eta`, `cuisineType`, `address`, `isActive`) VALUES(?,?,?,?,?,?,?)";
    private String SELECT_QUERY = "SELECT * FROM `restaurant` WHERE `restaurantId` = ?";
    private String UPDATE_QUERY = "UPDATE `restaurant` SET `name` = ?, `imagePath` = ?, `rating` = ?, `eta` = ?, `cuisineType` = ?, `address` = ?, `isActive` = ? WHERE `restaurantId` = ?";
    private String DELETE_QUERY = "DELETE FROM `restaurant` WHERE `restaurantId` = ?";
    private String SELECT_ALL_QUERY = "SELECT * FROM `restaurant`";

    public RestaurantDaoI() {
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
    public void addRestaurant(Restaurant restaurant) {
        PreparedStatement ptstmt = null;
        try {
            ptstmt = con.prepareStatement(INSERT_QUERY);
            ptstmt.setString(1, restaurant.getName());
            ptstmt.setString(2, restaurant.getImagePath());
            ptstmt.setFloat(3, restaurant.getRating());
            ptstmt.setInt(4, restaurant.getEta());
            ptstmt.setString(5, restaurant.getCuisineType());
            ptstmt.setString(6, restaurant.getAddress());
            ptstmt.setBoolean(7, restaurant.isActive());

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
    public Restaurant getRestaurant(int restaurantId) {
        PreparedStatement ptstmt = null;
        ResultSet result = null;
        Restaurant restaurant = null;
        try {
            ptstmt = con.prepareStatement(SELECT_QUERY);
            ptstmt.setInt(1, restaurantId);
            result = ptstmt.executeQuery();
            if (result.next()) {
                
                String name = result.getString("name");
                String imagePath = result.getString("imagePath");
                float rating = result.getFloat("rating");
                int eta = result.getInt("eta");
                String cuisineType = result.getString("cuisineType");
                String address = result.getString("address");
                boolean isActive = result.getBoolean("isActive");
                int restaurantOwnerId = result.getInt("restaurantOwnerId");
                
                restaurant = new Restaurant(restaurantId, name, imagePath, rating, eta, cuisineType, address, isActive, restaurantOwnerId);
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
        return restaurant;
    }

    @Override
    public void updateRestaurant(Restaurant restaurant) {
        PreparedStatement ptstmt = null;
        try {
            ptstmt = con.prepareStatement(UPDATE_QUERY);
            ptstmt.setString(1, restaurant.getName());
            ptstmt.setString(2, restaurant.getImagePath());
            ptstmt.setFloat(3, restaurant.getRating());
            ptstmt.setInt(4, restaurant.getEta());
            ptstmt.setString(5, restaurant.getCuisineType());
            ptstmt.setString(6, restaurant.getAddress());
            ptstmt.setBoolean(7, restaurant.isActive());
            ptstmt.setInt(8, restaurant.getRestaurantId());

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
    public void deleteRestaurant(int restaurantId) {
        PreparedStatement ptstmt = null;
        try {
            ptstmt = con.prepareStatement(DELETE_QUERY);
            ptstmt.setInt(1, restaurantId);
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
    public List<Restaurant> getAllRestaurant() {
        PreparedStatement ptstmt = null;
        ResultSet result = null;
        List<Restaurant> restaurantList = new ArrayList<>();
        try {
            ptstmt = con.prepareStatement(SELECT_ALL_QUERY);
            result = ptstmt.executeQuery();
            while (result.next()) {
            	int restaurantId = result.getInt("restaurantId");
            	String name = result.getString("name");
                String imagePath = result.getString("imagePath");
                float rating = result.getFloat("rating");
                int eta = result.getInt("eta");
                String cuisineType = result.getString("cuisineType");
                String address = result.getString("address");
                boolean isActive = result.getBoolean("isActive");
                int restaurantOwnerId = result.getInt("restaurantOwnerId");
                
                Restaurant restaurant = new Restaurant(restaurantId, name, imagePath, rating, eta, cuisineType, address, isActive, restaurantOwnerId);
                restaurantList.add(restaurant);
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
        return restaurantList;
    }
}
