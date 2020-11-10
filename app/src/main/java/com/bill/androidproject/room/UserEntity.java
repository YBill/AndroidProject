package com.bill.androidproject.room;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

/**
 * author : Bill
 * date : 2020/7/13
 * description :
 */

@Entity(tableName = "user_entity")
public class UserEntity {

    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "user_id")
    @NonNull
    public String id;

    public String userName;

    @Ignore
    public int age;

}
