package com.tap.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.tap.daoimpl.MenuDaoI;
import com.tap.model.Menu;

@WebServlet("/CartServlet")
public class CartServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        List<Menu> cart = (List<Menu>) session.getAttribute("cart");
        if (cart == null) {
            cart = new ArrayList<>();
        }

        int foodId = Integer.parseInt(req.getParameter("foodId"));
        MenuDaoI menuDao = new MenuDaoI();
        Menu foodItem = menuDao.getMenu(foodId);

        cart.add(foodItem);
        session.setAttribute("cart", cart);

        resp.getWriter().write("Item added to cart");
    }
}
