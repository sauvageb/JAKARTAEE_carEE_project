package com.example.app;


import com.example.app.dao.CarDao;
import com.example.app.dao.JdbcCarDao;

public final class DaoFactory {

    private DaoFactory() {
    }

    public static CarDao getCarDao() {
//        return new MemoryCarDao();
        return new JdbcCarDao();
//        return new JpaCarDao();
    }


//    public static SellerDao getSellerDao() {
//        return new JdbcSellerDao();
//    }
}
