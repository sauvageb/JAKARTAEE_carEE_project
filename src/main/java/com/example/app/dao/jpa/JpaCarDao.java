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
        EntityManager em = this.emf.createEntityManager();
        EntityTransaction et = em.getTransaction();
        try {
            et.begin();
            em.merge(car);
            et.commit();
        } catch (RuntimeException e) {
            if (et.isActive()) {
                et.rollback();
            }
        } finally {
            em.close();
        }
    }

    @Override
    public Car findById(Long id) {
        EntityManager em = this.emf.createEntityManager();
        Car car = em.find(Car.class, id);
        em.close();
        return car;
    }

    @Override
    public List<Car> findAll() {
        EntityManager em = this.emf.createEntityManager();
        List<Car> carList = em.createQuery("SELECT c FROM Car c", Car.class).getResultList();
        em.close();
        return carList;
    }

    @Override
    public void remove(Car car) {
        EntityManager em = this.emf.createEntityManager();
        EntityTransaction et = em.getTransaction();
        try {
            et.begin();
            Car c = em.merge(car);
            em.remove(c);
            et.commit();
        } catch (RuntimeException e) {
            if (et.isActive()) {
                et.rollback();
            }
        } finally {
            em.close();
        }
    }

    @Override
    public void removeById(Long id) {
        EntityManager em = this.emf.createEntityManager();
        EntityTransaction et = em.getTransaction();
        try {
            et.begin();
            Car c = em.find(Car.class, id);
            em.remove(c);
            et.commit();
        } catch (RuntimeException e) {
            if (et.isActive()) {
                et.rollback();
            }
        } finally {
            em.close();
        }
    }
}