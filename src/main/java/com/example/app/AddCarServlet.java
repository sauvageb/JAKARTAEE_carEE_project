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

@WebServlet(urlPatterns = "/add-car")
public class AddCarServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/addcar.jsp");
        rd.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String carName = req.getParameter("carName");
        String priceStr = req.getParameter("carPrice");
        Float price = null;
        try {
            price = Float.parseFloat(priceStr);

            CarDao carDAO = DaoFactory.getCarDao();
            Car newCar = new Car(carName, price);
            carDAO.addCar(newCar);

        } catch (NumberFormatException e) {
            // TODO : handle error
        }

    }
}
