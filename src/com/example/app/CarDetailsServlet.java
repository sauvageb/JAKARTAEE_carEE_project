package com.example.app;

import com.example.app.dao.CarDao;
import com.example.app.dao.DaoFactory;
import com.example.app.model.Car;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/details-car")
public class CarDetailsServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idStr = req.getParameter("id");
        Long id = null;

        try {
            id = Long.parseLong(idStr);
        } catch (NumberFormatException e) {
            //TODO
        }

        CarDao carDao = DaoFactory.getCarDao();
        Car car = carDao.findCarById(id);

        req.setAttribute("car", car);

        RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/cardetails.jsp");
        rd.forward(req, resp);
    }
}
