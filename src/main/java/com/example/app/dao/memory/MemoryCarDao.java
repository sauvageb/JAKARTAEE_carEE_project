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
            Category category1 = new MemoryCategoryDao().findById(1L);
            carList.add(new Car(++idSequence, "Polestar 2", "voiture électrique perfectionnée disponible en versions standard et longue autonomie. Cette bicorps entièrement électrique redéfinit le design et les performances.", 70000.500f, "https://thedriven.io/wp-content/uploads/2021/12/637249_20210801_Polestar_2_single_motor.jpg", category1));
            carList.add(new Car(++idSequence, "Kia EV6", "Crossover 100 % électrique qui ouvre une nouvelle ère et s’impose comme la référence pour les années à venir. Son design séduisant et distinctif ne manquera pas de susciter votre inspiration. ", 47990, "https://carsguide-res.cloudinary.com/image/upload/f_auto,fl_lossy,q_auto,t_cg_hero_large/v1/editorial/listicle/hero_image/2021-Kia-EV6-GT-Line-SUV-red-1001x565-1.jpg", category1));
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
