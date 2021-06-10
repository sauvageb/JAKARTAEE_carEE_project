package com.example.app.dao.memory;

import com.example.app.dao.CarDao;
import com.example.app.dao.exception.UnknownCarException;
import com.example.app.model.Car;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MemoryCarDao implements CarDao {

    private static List<Car> carList = new ArrayList<>();
    private static Long idSequence = 1L;

    public boolean create(Car car) {
        car.setId(++idSequence);
        return carList.add(car);
    }

    public void update(Car car) {
        int index = getCarIndexById(car.getId());
        if (index > -1) {
            carList.set(index, car);
        } else {
            throw new UnknownCarException(car.getId());
        }
    }

    public Car findById(Long id) {
        int index = getCarIndexById(id);
        if (index > -1) {
            Car car = carList.get(index);
            return car;
        } else {
            throw new UnknownCarException(id);
        }
    }

    public List<Car> findAll() {
        return Collections.unmodifiableList(carList);
    }

    public void remove(Car car) {
        removeById(car.getId());
    }

    public void removeById(Long id) {
        int index = getCarIndexById(id);
        if (index > -1) {
            carList.remove(index);
        } else {
            throw new UnknownCarException(id);
        }
    }

    private int getCarIndexById(Long id) {
        for (int i = 0; i < carList.size(); ++i) {
            Car car = carList.get(i);
            if (car.getId().equals(id)) {
                return i;
            }
        }
        return -1;
    }
}