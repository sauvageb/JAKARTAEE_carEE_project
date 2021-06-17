package com.example.app.dao;


import com.example.app.dao.jpa.JpaCarDao;
import com.example.app.dao.jpa.PersistenceManager;
import com.example.app.dao.memory.MemoryCarDao;
import com.example.app.dao.memory.MemoryCategoryDao;

public final class DaoFactory {

    private DaoFactory() {
    }

    public static CarDao getCarDao() {
        return new MemoryCarDao();
//        return new JdbcCarDao(ConnectionManager.getConnection());
//        return new JpaCarDao(PersistenceManager.getConnection());
    }


    public static CategoryDao getCategoryDao() {
        return new MemoryCategoryDao();
    }
}
