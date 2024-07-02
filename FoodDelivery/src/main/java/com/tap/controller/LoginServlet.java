package com.tap.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.tap.daoimpl.UserDaoI;
import com.tap.model.User;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	String username = req.getParameter("username");
        String password = req.getParameter("password");
        
        UserDaoI userDao = new UserDaoI();
        User user = userDao.getUserByUsername(username);
        
        // Validate username and password
        if (user != null && user.getPassword().equals(password)) {
            // If username and password are correct, store the user in session
            HttpSession session = req.getSession();
            session.setAttribute("user", user);
            
            // Redirect to the RestaurantServlet
            resp.sendRedirect(req.getContextPath() + "/RestaurantServlet");
        } else {
            // If username or password is incorrect, redirect back to login with error message
            resp.sendRedirect(req.getContextPath() + "/login.jsp?error=invalid");
        }
    }

}
