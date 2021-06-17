package com.example.app.servlet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(urlPatterns = "/login")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        boolean loggedIn = session != null && session.getAttribute("user") != null;
        if (loggedIn) {
            response.sendRedirect(request.getContextPath() + "/list-car");
        } else {
            RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/login.jsp");
            rd.forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        Map<String, String> messages = new HashMap<>();

        if (username == null || username.isEmpty()) {
            messages.put("username", "Please enter username");
        }

        if (password == null || password.isEmpty()) {
            messages.put("password", "Please enter password");
        }

        if (messages.isEmpty()) {

            if (username.equals("admin") && password.equals("admin")) {
                request.getSession().setAttribute("user", username);
                response.sendRedirect(request.getContextPath() + "/list-car");
                return;
            } else {
                messages.put("login", "Unknown login, please try again");
            }
        }
        request.setAttribute("messages", messages);
        request.getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
    }
}
