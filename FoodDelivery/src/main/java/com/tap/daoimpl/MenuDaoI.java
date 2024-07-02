package com.tap.daoimpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.tap.dao.MenuDao;
import com.tap.model.Menu;

public class MenuDaoI implements MenuDao {
    private Connection con = null;

    // SQL Queries
    private String INSERT_QUERY = "INSERT INTO `menu`(`name`, `price`, `description`, `imagePath`, `isAvailable`, `restaurantId`, `ratings`) VALUES(?,?,?,?,?,?,?)";
    private String SELECT_QUERY = "SELECT * FROM `menu` WHERE `menuId` = ?";
    private String UPDATE_QUERY = "UPDATE `menu` SET `name` = ?, `price` = ?, `description` = ?, `imagePath` = ?, `isAvailable` = ?, `restaurantId` = ?, `ratings` = ? WHERE `menuId` = ?";
    private String DELETE_QUERY = "DELETE FROM `menu` WHERE `menuId` = ?";
    private String SELECT_ALL_QUERY = "SELECT * FROM `menu` WHERE restaurantId = ?";

    public MenuDaoI() {
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
    public void addMenu(Menu menu) {
        PreparedStatement ptstmt = null;
        try {
            ptstmt = con.prepareStatement(INSERT_QUERY);
            ptstmt.setString(1, menu.getName());
            ptstmt.setInt(2, menu.getPrice());
            ptstmt.setString(3, menu.getDescription());
            ptstmt.setString(4, menu.getImagePath());
            ptstmt.setBoolean(5, menu.isAvailable());
            ptstmt.setInt(6, menu.getRestaurantId());
            ptstmt.setFloat(7, menu.getRatings());

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
    public Menu getMenu(int menuId) {
        PreparedStatement ptstmt = null;
        ResultSet result = null;
        Menu menu = null;
        try {
            ptstmt = con.prepareStatement(SELECT_QUERY);
            ptstmt.setInt(1, menuId);
            result = ptstmt.executeQuery();
            if (result.next()) {
                String name = result.getString("name");
                int price = result.getInt("price");
                String description = result.getString("description");
                String imagePath = result.getString("imagePath");
                boolean isAvailable = result.getBoolean("isAvailable");
                int restaurantId = result.getInt("restaurantId");
                float ratings = result.getFloat("ratings");
                
                menu = new Menu(menuId, name, price, description, imagePath, isAvailable, restaurantId, ratings);
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
        return menu;
    }

    @Override
    public void updateMenu(Menu menu) {
        PreparedStatement ptstmt = null;
        try {
            ptstmt = con.prepareStatement(UPDATE_QUERY);
            ptstmt.setString(1, menu.getName());
            ptstmt.setInt(2, menu.getPrice());
            ptstmt.setString(3, menu.getDescription());
            ptstmt.setString(4, menu.getImagePath());
            ptstmt.setBoolean(5, menu.isAvailable());
            ptstmt.setInt(6, menu.getRestaurantId());
            ptstmt.setFloat(7, menu.getRatings());
            ptstmt.setInt(8, menu.getMenuId());

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
    public void deleteMenu(int menuId) {
        PreparedStatement ptstmt = null;
        try {
            ptstmt = con.prepareStatement(DELETE_QUERY);
            ptstmt.setInt(1, menuId);
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
    public List<Menu> getAllMenu(int restaurantId) {
        PreparedStatement ptstmt = null;
        ResultSet result = null;
        List<Menu> menuList = new ArrayList<>();
        try {
            ptstmt = con.prepareStatement(SELECT_ALL_QUERY);
            ptstmt.setInt(1, restaurantId);
            result = ptstmt.executeQuery();
            while (result.next()) {
                int menuId = result.getInt("menuId");
                String name = result.getString("name");
                int price = result.getInt("price");
                String description = result.getString("description");
                String imagePath = result.getString("imagePath");
                boolean isAvailable = result.getBoolean("isAvailable");
                float ratings = result.getFloat("ratings");
                
                Menu menu = new Menu(menuId, name, price, description, imagePath, isAvailable, restaurantId, ratings);
                menuList.add(menu);
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
        return menuList;
    }
}
		
