package com.bill.androidproject.paging.db;

import androidx.annotation.Nullable;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

/**
 * author : Bill
 * date : 2020/7/13
 * description :
 */

@Entity(tableName = "student_entity")
public class StudentEntity {

    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name = "name")
    public String name;

    @Ignore
    public int age;

    @Override
    public boolean equals(@Nullable Object obj) {
        if (obj == null)
            return false;

        if (this == obj)
            return true;

        return super.equals(obj);
    }
}
