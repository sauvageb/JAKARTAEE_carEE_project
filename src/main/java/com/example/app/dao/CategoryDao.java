package com.example.app.dao;

import com.example.app.model.Category;

import java.util.List;

public interface CategoryDao {

    boolean create(Category category);

    void update(Category category);

    Category findById(Long id);

    List<Category> findAll();

    void remove(Category category);

    void removeById(Long id);
}