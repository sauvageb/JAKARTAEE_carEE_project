package com.example.app.servlet;

import com.example.app.dao.CarDao;
import com.example.app.dao.DaoFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/delete-car")
public class DeleteCarServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String carIdStr = req.getParameter("id");

        try {
            Long carId = Long.parseLong(carIdStr);
            CarDao carDAO = DaoFactory.getCarDao();
            carDAO.removeById(carId);
        } catch (NumberFormatException e) {
            //TODO
        }

        resp.sendRedirect(req.getContextPath() + CarListServlet.URL);
    }
}
