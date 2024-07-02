package com.tap.controller;


import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tap.daoimpl.UserDaoI;
import com.tap.model.User;
import com.tap.model.UserRole;

@WebServlet("/SignupServlet")
public class SignupServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve user details from the signup form
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        int phone = Integer.parseInt(request.getParameter("phone")); // Parse as integer
        String address = request.getParameter("address");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        UserRole role = UserRole.valueOf(request.getParameter("role")); // Parse as UserRole enum

        // Create a new User object
        User user = new User();
        user.setName(name);
        user.setEmail(email);
        user.setPhoneNo(phone);
        user.setAddress(address);
        user.setUsername(username);
        user.setPassword(password);
        user.setRole(role);

        // Create an instance of UserDaoI
        UserDaoI userDao = new UserDaoI();

        // Call the method to add user details to the database
        userDao.addUser(user);

        // Redirect to a confirmation page
        response.sendRedirect("signupConfirmation.jsp");
    }
}
