package com.tap.controller;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.tap.daoimpl.OrderHistoryDaoI;
import com.tap.model.Menu;
import com.tap.model.OrderHistory;

@WebServlet("/CheckoutServlet")
public class CheckoutServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        List<Menu> cart = (List<Menu>) session.getAttribute("cart");

        if (cart != null && !cart.isEmpty()) {
            OrderHistoryDaoI orderHistoryDao = new OrderHistoryDaoI();
            for (Menu foodItem : cart) {
                OrderHistory order = new OrderHistory();
                order.setOrderHistoryId(foodItem.getMenuId());
                orderHistoryDao.addOrderHistory(order);
            }
            session.removeAttribute("cart");
            resp.getWriter().write("Your order has been submitted");
        } else {
            resp.getWriter().write("Cart is empty");
        }
    }
}
