package com.example.app.servlet;

import com.example.app.dao.CategoryDao;
import com.example.app.dao.DaoFactory;
import com.example.app.model.Category;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = CategoryListServlet.URL)
public class CategoryListServlet extends HttpServlet {

    public static final String URL = "/list-category";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        CategoryDao categoryDao = DaoFactory.getCategoryDao();
        List<Category> categoryList = categoryDao.findAll();

        req.setAttribute("categoryList", categoryList);

        RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/categorylist.jsp");
        rd.forward(req, resp);
    }
}
