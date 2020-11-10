package com.bill.androidproject.paging.db;

import androidx.paging.DataSource;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

/**
 * author : Bill
 * date : 2020/7/13
 * description :
 */

@Dao
public interface StudentDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertUser(List<StudentEntity> list);

    @Query("SELECT * FROM student_entity LIMIT :size OFFSET :page")
    List<StudentEntity> getAllStudents(int page, int size);

    @Query("SELECT * FROM student_entity ORDER BY id ASC")
    DataSource.Factory<Integer, StudentEntity> getAllStudents();

}
