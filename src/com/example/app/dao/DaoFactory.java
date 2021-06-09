package com.example.app.dao;


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
