package com.example.app.dao;

import com.example.app.model.Car;

import java.util.List;

public interface CarDao {

    boolean create(Car car);

    void update(Car car);

    Car findById(Long id);

    List<Car> findAll();

    void remove(Car car);

    void removeById(Long id);
}