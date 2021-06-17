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

@WebServlet(urlPatterns = "/auth/add-category")
public class AddCategoryServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/auth/addcategory.jsp");
        rd.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String categoryName = req.getParameter("categoryName");
        try {
            CategoryDao categoryDao = DaoFactory.getCategoryDao();
            Category newCategory = new Category(categoryName);
            categoryDao.create(newCategory);

        } catch (NumberFormatException e) {
            // TODO : handle error
        }

        resp.sendRedirect(req.getContextPath() + CategoryListServlet.URL);
    }
}
