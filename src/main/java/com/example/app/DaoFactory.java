package com.example.app;


public final class DaoFactory {

    private DaoFactory() {
    }

    public static com.example.app.dao.CarDao getCarDao() {
//        return new MemoryCarDao();
        return new JdbcCarDao();
//        return new JpaCarDao();
    }


//    public static SellerDao getSellerDao() {
//        return new JdbcSellerDao();
//    }
}
