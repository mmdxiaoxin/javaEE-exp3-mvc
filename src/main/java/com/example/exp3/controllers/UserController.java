package com.example.exp3.controllers;

import com.example.exp3.service.UsersService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "UserController", value = "/user-ctrl")
public class UserController extends HttpServlet {
    private final UsersService usersService = new UsersService();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if ("login".equals(action)) {
            loginUser(request, response);
        } else {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid action");
        }
    }

    private void loginUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        if (usersService.validateUser(username, password)) {
            request.getSession().setAttribute("username", username);
            response.sendRedirect(request.getContextPath() + "/book-ctrl?action=list");
        } else {
            request.setAttribute("message", "用户名或密码错误");
            request.getRequestDispatcher("/views/login.jsp").forward(request, response);
        }
    }
}
