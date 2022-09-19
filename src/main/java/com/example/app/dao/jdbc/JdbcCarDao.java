package com.example.app.dao.jdbc;

import com.example.app.dao.CarDao;
import com.example.app.model.Car;
import com.example.app.model.Category;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JdbcCarDao implements CarDao {

    private final Connection connection;

    public JdbcCarDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public boolean create(Car car) {
        int isCreated = 0;
        String query = "INSERT INTO cars (name, description, price) VALUES(?,?)";
        try (PreparedStatement pst = this.connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            pst.setString(1, car.getName());
            pst.setObject(2, car.getPrice());
            isCreated = pst.executeUpdate();

            this.connection.commit();

            // Fetching generated id from database during insert
            ResultSet resultSet = pst.getGeneratedKeys();
            resultSet.next();
            Long id = resultSet.getLong(1);

            if (id != null) {
                car.setId(id);
            }

        } catch (SQLException e1) {
            System.out.println("mauvaise ID, rollbacked");
            e1.printStackTrace();
            try {
                this.connection.rollback();
            } catch (SQLException e2) {
                System.out.println("mauvaise ID, no rollback");
                e2.printStackTrace();
            }
        }
        return isCreated > 0;
    }

    @Override
    public void update(Car product) {
        // TODO
    }

    @Override
    public Car findById(Long id) {
        String query = "SELECT cars.id AS id, cars.name AS name, cars.price AS price, categories.id AS category_id, categories.name AS category_name FROM cars INNER JOIN categories WHERE cars.id = ?";
        Car foundCar = null;
        try (PreparedStatement pst = this.connection.prepareStatement(query)) {
            pst.setLong(1, id);
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                foundCar = mapToCar(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return foundCar;
    }

    @Override
    public List<Car> findAll() {
        String query = "SELECT * FROM cars";
        List<Car> placeList = new ArrayList<>();
        try (Statement st = this.connection.createStatement()) {
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                placeList.add(mapToCar(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return placeList;
    }

    private Car mapToCar(ResultSet rs) throws SQLException {
        Long id = rs.getLong("id");
        String name = rs.getString("name");
        String description = rs.getString("description");
        float price = rs.getFloat("price");
        String pictureUrl = rs.getString("pictureUrl");
        Category category = new Category(rs.getLong("category_id"), rs.getString("category_name"));
        return new Car(id, name, description, price, pictureUrl, category);
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
