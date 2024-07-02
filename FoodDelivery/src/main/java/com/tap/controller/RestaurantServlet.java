package com.tap.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tap.dao.RestaurantDao;
import com.tap.daoimpl.RestaurantDaoI;
import com.tap.model.Restaurant;

@WebServlet("/RestaurantServlet")
public class RestaurantServlet extends HttpServlet {
	
	@Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	RestaurantDaoI restaurant = new RestaurantDaoI();
    	List<Restaurant> restaurants = restaurant.getAllRestaurant();
    	req.setAttribute("restaurants", restaurants);
    	RequestDispatcher rd = req.getRequestDispatcher("restaurant.jsp");
    	rd.forward(req, resp);
    }
}
