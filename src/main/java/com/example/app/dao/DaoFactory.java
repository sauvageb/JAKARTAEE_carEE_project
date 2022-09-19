package com.example.app.dao;


import com.example.app.dao.memory.MemoryCarDao;
import com.example.app.dao.memory.MemoryCategoryDao;

public final class DaoFactory {

    private DaoFactory() {
    }

    public static CarDao getCarDao() {
        return new MemoryCarDao();
    }


    public static CategoryDao getCategoryDao() {
        return new MemoryCategoryDao();
    }
}
