package com.example.app.dao.jpa;

import com.example.app.model.Car;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import java.util.List;

public class JpaCarDao implements com.example.app.dao.CarDao {

    private final EntityManagerFactory emf;

    public JpaCarDao(EntityManagerFactory entityManagerFactory) {
        emf = entityManagerFactory;
    }

    @Override
    public boolean create(Car car) {
        EntityManager em = this.emf.createEntityManager();
        EntityTransaction et = em.getTransaction();
        try {
            et.begin();
            em.persist(car);
            et.commit();
        } catch (RuntimeException e) {
            if (et.isActive()) {
                et.rollback();
            }
            return false;
        } finally {
            em.close();
        }
        return true;
    }

    @Override
    public void update(Car car) {
        // TODO
    }

    @Override
    public Car findById(Long id) {
        // TODO
        return null;
    }

    @Override
    public List<Car> findAll() {
        // TODO
        return null;
    }

    @Override
    public void remove(Car car) {
        // TODO
    }

    @Override
    public void removeById(Long id) {
        // TODO
    }
}