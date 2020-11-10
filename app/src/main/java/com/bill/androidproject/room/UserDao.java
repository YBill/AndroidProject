package com.bill.androidproject.room;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

/**
 * author : Bill
 * date : 2020/7/13
 * description :
 */

@Dao
public interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertUser(UserEntity... users);

    @Delete()
    void deleteUser(UserEntity user);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void updateUser(UserEntity user);

    @Query("SELECT * FROM user_entity")
    List<UserEntity> getAllUsers();

    @Query("SELECT * FROM USER_ENTITY WHERE user_id = :id")
    UserEntity findUserById(String id);


}
