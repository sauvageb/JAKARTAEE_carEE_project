package com.example.app.dao.memory;

import com.example.app.dao.CarDao;
import com.example.app.dao.exception.UnknownCarException;
import com.example.app.model.Car;
import com.example.app.model.Category;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MemoryCarDao implements CarDao {

    private static List<Car> carList = new ArrayList<>();
    private static Long idSequence = 0L;

    public MemoryCarDao() {
        if (idSequence < 1) {
            Category category1 = new Category("Exemple Category 1");
            Category category2 = new Category("Exemple Category 2");

            carList.add(new Car(++idSequence, "Tesla", "En l'absence de moteur à combustion interne, la zone de déformation peut mieux réduire les risques de blessures pour les occupants.", 45000f, category1));
            carList.add(new Car(++idSequence, "Mustang-mach-e", "Premier SUV 100 % électrique de Ford. Découvrez son design élégamment sculpté, ses technologies de pointe et son autonomie électrique de 610 km.", 70000.500f, category2));
        }
    }

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