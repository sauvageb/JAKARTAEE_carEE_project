package com.example.app.dao.jdbc;

import com.example.app.dao.CategoryDao;
import com.example.app.model.Category;

import java.sql.Connection;
import java.util.List;

public class JdbcCategoryDao implements CategoryDao {

    private final Connection connection;

    public JdbcCategoryDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public boolean create(Category category) {
        return false;
    }

    @Override
    public void update(Category category) {

    }

    @Override
    public Category findById(Long id) {
        return null;
    }

    @Override
    public List<Category> findAll() {
        return null;
    }

    @Override
    public void remove(Category category) {

    }

    @Override
    public void removeById(Long id) {

    }
}