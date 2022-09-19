package com.example.app.dao.memory;

import com.example.app.dao.CategoryDao;
import com.example.app.dao.exception.UnknownCarException;
import com.example.app.model.Car;
import com.example.app.model.Category;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MemoryCategoryDao implements CategoryDao {

    private static List<Category> categoryList = new ArrayList<>();
    private static Long idSequence = 0L;

    public MemoryCategoryDao() {
        if (idSequence < 1) {
            categoryList.add(new Category(++idSequence, "Électrique"));
        }
    }

    @Override
    public boolean create(Category category) {
        category.setId(++idSequence);
        return categoryList.add(category);
    }

    @Override
    public void update(Category category) {
        int index = getCarIndexById(category.getId());
        if (index > -1) {
            categoryList.set(index, category);
        } else {
            throw new UnknownCarException(category.getId());
        }
    }


    @Override
    public Category findById(Long id) {
        int index = getCarIndexById(id);
        if (index > -1) {
            Category category = categoryList.get(index);
            return category;
        } else {
            throw new UnknownCarException(id);
        }
    }

    @Override
    public List<Category> findAll() {
        return Collections.unmodifiableList(categoryList);
    }

    @Override
    public void remove(Category category) {
        MemoryCarDao carDao = new MemoryCarDao();
        List<Long> ids = new ArrayList<>();
        for (Car car : carDao.findAll()) {
            if (car.getCategory().getId().equals(category.getId())) {
                ids.add(car.getId());
            }
        }
        ids.forEach(carId -> carDao.removeById(carId));
        removeById(category.getId());
    }

    @Override
    public void removeById(Long id) {
        int index = getCarIndexById(id);
        if (index > -1) {

            List<Long> ids = new ArrayList<>();
            MemoryCarDao carDao = new MemoryCarDao();
            for (Car car : carDao.findAll()) {
                if (car.getCategory().getId().equals(categoryList.get(index).getId())) {
                    ids.add(car.getId());
                }
            }
            ids.forEach(carId -> carDao.removeById(carId));

            categoryList.remove(index);

        } else {
            throw new UnknownCarException(id);
        }
    }

    private int getCarIndexById(Long id) {
        for (int i = 0; i < categoryList.size(); ++i) {
            Category category = categoryList.get(i);
            if (category.getId().equals(id)) {
                return i;
            }
        }
        return -1;
    }
}
