package com.example.app.dao;

import com.example.app.model.Car;

import java.util.List;

class JpaCarDao implements com.example.app.dao.CarDao {

    // TODO
    @Override
    public void addCar(Car car) {
//        EntityManager em = ..
//        em.persist(car);
//        em.close();
    }

    @Override
    public void updateCar(Car car) {

    }

    @Override
    public Car findCarById(Long id) {
        return null;
    }

    @Override
    public List<Car> findAll() {
        return null;
    }

    @Override
    public void removeCar(Car car) {

    }

    @Override
    public void removeCar(Long id) {

    }
}