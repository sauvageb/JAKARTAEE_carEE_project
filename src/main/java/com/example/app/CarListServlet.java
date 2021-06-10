package com.example.app;

import com.example.app.dao.CarDao;
import com.example.app.model.Car;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = {"/", "/list-car"})
public class CarListServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        CarDao carDao = DaoFactory.getCarDao();
        List<Car> carList = carDao.findAll();

        req.setAttribute("carList", carList);

        RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/carlist.jsp");
        rd.forward(req, resp);
    }
}
