package com.tap.daoimpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.tap.dao.UserDao;
import com.tap.model.User;
import com.tap.model.UserRole;

public class UserDaoI implements UserDao {

    private Connection con = null;

    // SQL queries
    private String INSERT_QUERY = "INSERT INTO `user`(`name`, `email`, `phoneNo`, `address`, `username`, `password`, `role`) VALUES(?,?,?,?,?,?,?)";
    private String SELECT_QUERY = "SELECT * FROM `user` WHERE `userId` = ?";
    private String UPDATE_QUERY = "UPDATE `user` SET `name` = ?, `email` = ?, `phoneNo` = ?, `address` = ?, `username` = ?, `password` = ?, `role` = ?  WHERE userId = ?";
    private String DELETE_QUERY = "DELETE FROM `user` WHERE `userId` = ?";
    private String SELECT_ALL_QUERY = "SELECT * FROM `user`";

    // Constructor to establish database connection
    public UserDaoI() {
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

    // Method to add a user to the database
    @Override
    public void addUser(User user) {
        PreparedStatement ptstmt = null;
        try {
            ptstmt = con.prepareStatement(INSERT_QUERY);

            ptstmt.setString(1, user.getName());
            ptstmt.setString(2, user.getEmail());
            ptstmt.setInt(3, user.getPhoneNo());
            ptstmt.setString(4, user.getAddress());
            ptstmt.setString(5, user.getUsername());
            ptstmt.setString(6, user.getPassword());
            ptstmt.setString(7, user.getRole().toString()); // Assuming role is stored as a string in the database

            ptstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Close resources
            try {
                if (ptstmt != null) {
                    ptstmt.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    // Method to retrieve a user from the database by userId
    @Override
    public User getUser(int userId) {
        PreparedStatement ptstmt = null;
        User user = null;
        ResultSet result = null;

        try {
            ptstmt = con.prepareStatement(SELECT_QUERY);
            ptstmt.setInt(1, userId);
            result = ptstmt.executeQuery();
            while (result.next()) {
                String name = result.getString("name");
                String email = result.getString("email");
                int phoneNo = result.getInt("phoneNo");
                String address = result.getString("address");
                String username = result.getString("username");
                String password = result.getString("password");
                String roleString = result.getString("role");
                UserRole role = UserRole.valueOf(roleString);
                LocalDateTime createdDate = result.getTimestamp("createdDate").toLocalDateTime();
                LocalDateTime lastLogin = result.getTimestamp("lastLogin").toLocalDateTime();

                user = new User(userId, name, email, phoneNo, address, username, password, role, createdDate, lastLogin);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    // Method to update a user in the database
    @Override
    public void updateUser(User user) {
        PreparedStatement ptstmt = null;
        try {
            ptstmt = con.prepareStatement(UPDATE_QUERY);
            ptstmt.setString(1, user.getName());
            ptstmt.setString(2, user.getEmail());
            ptstmt.setInt(3, user.getPhoneNo());
            ptstmt.setString(4, user.getAddress());
            ptstmt.setString(5, user.getUsername());
            ptstmt.setString(6, user.getPassword());
            ptstmt.setString(7, user.getRole().toString()); // Assuming role is stored as a string in the database
            ptstmt.setInt(8, user.getUserId());

            ptstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Method to delete a user from the database by userId
    @Override
    public void deleteUser(int userId) {
        PreparedStatement ptstmt = null;
        try {
            ptstmt = con.prepareStatement(DELETE_QUERY);
            ptstmt.setInt(1, userId);
            ptstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Method to retrieve all users from the database
    @Override
    public List<User> getAllUser() {
        PreparedStatement ptstmt = null;
        ResultSet result = null;
        List<User> userList = new ArrayList<User>();

        try {
            ptstmt = con.prepareStatement(SELECT_ALL_QUERY);
            result = ptstmt.executeQuery();

            while (result.next()) {
                int userId = result.getInt("userId");
                String name = result.getString("name");
                String email = result.getString("email");
                int phoneNo = result.getInt("phoneNo");
                String address = result.getString("address");
                String username = result.getString("username");
                String password = result.getString("password");
                String roleString = result.getString("role");
                UserRole role = UserRole.valueOf(roleString);
                LocalDateTime createdDate = result.getTimestamp("createdDate").toLocalDateTime();
                LocalDateTime lastLogin = result.getTimestamp("lastLogin").toLocalDateTime();

                User user = new User(userId, name, email, phoneNo, address, username, password, role, createdDate, lastLogin);
                userList.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userList;
    }
    
    
    @Override
    public User getUserByUsername(String username) {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        User user = null;

        try {
            String query = "SELECT * FROM `user` WHERE `username` = ?";
            pstmt = con.prepareStatement(query);
            
            pstmt.setString(1, username);
            rs = pstmt.executeQuery();
            
            if (rs.next()) {
                int userId = rs.getInt("userId");
                String name = rs.getString("name");
                String email = rs.getString("email");
                int phoneNo = rs.getInt("phoneNo");
                String address = rs.getString("address");
                String password = rs.getString("password");
                UserRole role = UserRole.valueOf(rs.getString("role"));
                LocalDateTime createdDate = rs.getTimestamp("createdDate").toLocalDateTime();
                LocalDateTime lastLogin = rs.getTimestamp("lastLogin").toLocalDateTime();
                
                // Create a new User object
                user = new User(userId, name, email, phoneNo, address, username, password, role, createdDate, lastLogin);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (pstmt != null) {
                    pstmt.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        
        return user;
    }

}
