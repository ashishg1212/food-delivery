package com.tap.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tap.daoimpl.RestaurantDaoI;
import com.tap.model.Restaurant;

@WebServlet("/index")
public class IndexServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve the list of restaurants from the database
        RestaurantDaoI restaurantDao = new RestaurantDaoI();
        List<Restaurant> restaurants = restaurantDao.getAllRestaurant();
        
        // Set the list of restaurants in the request attribute
        request.setAttribute("restaurants", restaurants);
        
        // Forward the request to the index.jsp page to display the restaurants
        request.getRequestDispatcher("/index.jsp").forward(request, response);
    }
}
