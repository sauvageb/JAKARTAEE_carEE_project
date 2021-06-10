package com.example.app.dao;


import com.example.app.dao.jdbc.ConnectionManager;
import com.example.app.dao.jdbc.JdbcCarDao;
import com.example.app.dao.jpa.JpaCarDao;
import com.example.app.dao.jpa.PersistenceManager;

public final class DaoFactory {

    private DaoFactory() {
    }

    public static CarDao getCarDao() {
//        return new MemoryCarDao();
//        return new JdbcCarDao(ConnectionManager.getConnection());
        return new JpaCarDao(PersistenceManager.getConnection());
    }


//    public static SellerDao getSellerDao() {
//        return new JdbcSellerDao();
//    }
}
