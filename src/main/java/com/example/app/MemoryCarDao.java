package com.example.app;

import com.example.app.model.Car;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class MemoryCarDao implements com.example.app.dao.CarDao {

    private static List<Car> carList = new ArrayList();
    private static Long idSequence = 1L;

    public void addCar(Car product) {
        Long var1 = idSequence;
        idSequence = idSequence + 1L;
        product.setId(var1);
        carList.add(product);
    }

    public void updateCar(Car product) {
        int index = getCarIndexById(product.getId());
        if (index > -1) {
            carList.set(index, product);
        } else {
            throw new UnknownCarException(product.getId());
        }
    }

    public Car findCarById(Long id) {
        int index = getCarIndexById(id);
        if (index > -1) {
            Car product = carList.get(index);
            return product;
        } else {
            throw new UnknownCarException(id);
        }
    }

    public List<Car> findAll() {
        return Collections.unmodifiableList(carList);
    }

    public void removeCar(Car product) {
        removeCar(product.getId());
    }

    public void removeCar(Long id) {
        int index = getCarIndexById(id);
        if (index > -1) {
            carList.remove(index);
        } else {
            throw new UnknownCarException(id);
        }
    }

    private int getCarIndexById(Long id) {
        for (int i = 0; i < carList.size(); ++i) {
            Car product = carList.get(i);
            if (product.getId().equals(id)) {
                return i;
            }
        }
        return -1;
    }
}