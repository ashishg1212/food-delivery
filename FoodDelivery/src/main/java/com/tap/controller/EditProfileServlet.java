package com.tap.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.tap.model.User;
import com.tap.daoimpl.UserDaoI;

@WebServlet("/EditProfileServlet")
public class EditProfileServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve user details from the form
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        int phone = Integer.parseInt(request.getParameter("phone"));
        String address = request.getParameter("address");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        
        // Retrieve user object from session
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        
        // Update user details
        user.setName(name);
        user.setEmail(email);
        user.setPhoneNo(phone);
        user.setAddress(address);
        user.setUsername(username);
        user.setPassword(password);
        
        // Update user details in the database
        UserDaoI userDao = new UserDaoI();
        userDao.updateUser(user);
        
        // Redirect to profile page or any other page
        response.sendRedirect("RestaurantServlet");
    }
}
