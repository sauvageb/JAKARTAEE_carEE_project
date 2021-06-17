package com.example.app.servlet;

import com.example.app.dao.CategoryDao;
import com.example.app.dao.DaoFactory;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/delete-category")
public class DeleteCategoryServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String categoryIdStr = req.getParameter("id");

        try {
            Long categoryId = Long.parseLong(categoryIdStr);
            CategoryDao categoryDao = DaoFactory.getCategoryDao();
            categoryDao.removeById(categoryId);

        } catch (NumberFormatException e) {
            //TODO
        }

        resp.sendRedirect(req.getContextPath() + CategoryListServlet.URL);
    }
}
