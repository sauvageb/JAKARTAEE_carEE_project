package com.example.app.dao;

import com.example.app.model.Car;

import java.util.List;

public interface CarDao {

    void addCar(Car product);

    void updateCar(Car product);

    Car findCarById(Long id);

    List<Car> findAll();

    void removeCar(Car product);

    void removeCar(Long id);
}