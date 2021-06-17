package com.example.app.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/auth/leave")
public class LogoutProcessServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String leave = req.getParameter("leave");
        boolean leaveOk = Boolean.parseBoolean(leave);
        if (leaveOk) {
            req.getSession().invalidate();
        }
        resp.sendRedirect(req.getContextPath() + "/login");
    }
}
