package com.bill.androidproject.room;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.bill.androidproject.paging.db.StudentDao;
import com.bill.androidproject.paging.db.StudentEntity;

/**
 * author : Bill
 * date : 2020/7/13
 * description :
 */

@Database(entities = {UserEntity.class, StudentEntity.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

    public abstract UserDao userDao();

    public abstract StudentDao studentDao();

}
