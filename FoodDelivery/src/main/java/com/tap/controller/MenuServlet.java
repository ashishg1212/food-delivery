package com.tap.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tap.daoimpl.MenuDaoI;
import com.tap.model.Menu;

@WebServlet("/MenuServlet")
public class MenuServlet extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		MenuDaoI menu = new MenuDaoI();
		String restaurantIdStr = req.getParameter("restaurantId");
		int restaurantId = Integer.parseInt(restaurantIdStr);
		List<Menu> allMenu = menu.getAllMenu(restaurantId);
		
		req.setAttribute("allMenu", allMenu);
		RequestDispatcher rd = req.getRequestDispatcher("menu.jsp");
		rd.forward(req, resp);
	}
}
