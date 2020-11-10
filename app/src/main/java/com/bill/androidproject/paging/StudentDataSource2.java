package com.bill.androidproject.paging;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.paging.PageKeyedDataSource;

import com.bill.androidproject.paging.db.StudentEntity;
import com.bill.androidproject.room.DbHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * author : Bill
 * date : 2020/7/16
 * description : 适用于网络数据加载
 */
public class StudentDataSource2 extends PageKeyedDataSource<Integer, StudentEntity> {

    private void initData() {
        List<StudentEntity> list = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            StudentEntity entity = new StudentEntity();
            entity.name = "Bill_" + (i + 1);
            list.add(entity);
        }

        DbHelper.getInstance().getStudentDao().insertUser(list);

    }

    @Override
    public void loadInitial(@NonNull final LoadInitialParams<Integer> params, @NonNull final LoadInitialCallback<Integer, StudentEntity> callback) {
        Log.e("Bill", "loadInitial:" + params.requestedLoadSize);

        new Thread(new Runnable() {
            @Override
            public void run() {
                List<StudentEntity> list = DbHelper.getInstance().getStudentDao().getAllStudents(0, params.requestedLoadSize);
                if (list == null || list.size() == 0) {
                    initData();
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    list = DbHelper.getInstance().getStudentDao().getAllStudents(0, params.requestedLoadSize);
                }

                Log.d("Bill", "getData:" + (list == null ? "null" : list.size()));

                int previousPageKey = 1; // 上一页数据，在loadBefore中获取的key就是这个值
                int nextPageKey = 2; // 下一页数据，在loadAfter中获取的key就是这个值
                callback.onResult(list, previousPageKey, nextPageKey);
            }
        }).start();
    }

    @Override
    public void loadAfter(@NonNull final LoadParams<Integer> params, @NonNull final LoadCallback<Integer, StudentEntity> callback) {
        Log.e("Bill", "loadAfter:" + params.key + "|" + params.requestedLoadSize);

        new Thread(new Runnable() {
            @Override
            public void run() {
                int offset; // 因为不是网络请求的数据，是数据库获取，数据库语句是根据偏移拿去数据，这里计算一下
                offset = params.key * params.requestedLoadSize;
                List<StudentEntity> list = DbHelper.getInstance().getStudentDao().getAllStudents(offset, params.requestedLoadSize);

                Log.d("Bill", "getData:" + (list == null ? "null" : list.size()));

                int adjacentPageKey = params.key; // 最开始为loadInitial中传入的nextPageKey值，后面为loadAfter中adjacentPageKey的值
                callback.onResult(list, ++adjacentPageKey);
            }
        }).start();
    }

    @Override
    public void loadBefore(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Integer, StudentEntity> callback) {
        Log.e("Bill", "loadBefore:" + params.key + "|" + params.requestedLoadSize);
    }

}
