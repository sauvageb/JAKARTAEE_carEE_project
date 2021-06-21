package com.example.app.servlet;

import com.example.app.dao.CarDao;
import com.example.app.dao.CategoryDao;
import com.example.app.dao.DaoFactory;
import com.example.app.model.Car;
import com.example.app.model.Category;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/auth/add-car")
public class AddCarServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        CategoryDao categoryDao = DaoFactory.getCategoryDao();
        List<Category> categoryList = categoryDao.findAll();

        req.setAttribute("categoryList", categoryList);

        RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/auth/addcar.jsp");
        rd.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String carName = req.getParameter("carName");
        String carDescription = req.getParameter("carDescription");
        String priceStr = req.getParameter("carPrice");

        String categoryIdStr = req.getParameter("carCategory");

        Category category = null;
        try {
            CategoryDao categoryDao = DaoFactory.getCategoryDao();
            Long categoryId = Long.parseLong(categoryIdStr);
            category = categoryDao.findById(categoryId);
        } catch (NumberFormatException e) {
            // TODO : handle error
        }

        Float price;
        try {
            price = Float.parseFloat(priceStr);

            CarDao carDAO = DaoFactory.getCarDao();
            Car newCar = new Car(carName, carDescription, price, category);
            carDAO.create(newCar);

        } catch (NumberFormatException e) {
            // TODO : handle error
        }

        resp.sendRedirect(req.getContextPath() + HomeServlet.URL);

    }
}
