package com.bill.androidproject.room;

import androidx.room.Room;

import com.bill.androidproject.MyApplication;
import com.bill.androidproject.paging.db.StudentDao;

/**
 * author : Bill
 * date : 2020/7/15
 * description :
 */
public class DbHelper {

    private static class SingletonHolder {
        private final static DbHelper instance = new DbHelper();
    }

    public static DbHelper getInstance() {
        return SingletonHolder.instance;
    }

    private AppDatabase database;

    private DbHelper() {
        database = Room.databaseBuilder(MyApplication.mContext, AppDatabase.class, "room_test.db")
                .build();
    }

    public AppDatabase getDatabase() {
        return database;
    }

    public UserDao getUserDao() {
        return database.userDao();
    }

    public StudentDao getStudentDao() {
        return database.studentDao();
    }
}
