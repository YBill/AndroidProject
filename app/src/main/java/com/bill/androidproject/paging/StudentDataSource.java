package com.bill.androidproject.paging;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.paging.ItemKeyedDataSource;

import com.bill.androidproject.paging.db.StudentEntity;
import com.bill.androidproject.room.DbHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * author : Bill
 * date : 2020/7/15
 * description :根据 数据的第N项加载第N-1和N+1项内容
 */
public class StudentDataSource extends ItemKeyedDataSource<Integer, StudentEntity> {

    private void initData() {
        List<StudentEntity> list = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            StudentEntity entity = new StudentEntity();
            entity.name = "Bill_" + (i + 1);
            list.add(entity);
        }

        DbHelper.getInstance().getStudentDao().insertUser(list);

    }

    private void getData(final int offset, final int size, final LoadCallback<StudentEntity> callback) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                List<StudentEntity> list = DbHelper.getInstance().getStudentDao().getAllStudents(offset, size);
                if (list == null || list.size() == 0) {
                    initData();
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    list = DbHelper.getInstance().getStudentDao().getAllStudents(offset, size);
                }

                Log.d("Bill", "getData:" + (list == null ? "null" : list.size()));

                callback.onResult(list);
            }
        }).start();

    }

    @Override
    public void loadInitial(@NonNull LoadInitialParams<Integer> params, @NonNull LoadInitialCallback<StudentEntity> callback) {
        Log.e("Bill", "loadInitial:" + params.requestedLoadSize);

        getData(0, params.requestedLoadSize, callback);
    }

    @Override
    public void loadAfter(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<StudentEntity> callback) {
        // params.key 为最后一条数据的getKey()
        // params.requestedLoadSize 为分页数,PagedList.Config.Builder的setPageSize参数
        Log.e("Bill", "loadAfter:" + params.key + "|" + params.requestedLoadSize);

        getData(params.key, params.requestedLoadSize, callback);

    }

    @Override
    public void loadBefore(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<StudentEntity> callback) {
        // params.key 为第一条数据的getKey()
        // params.requestedLoadSize 为分页数,PagedList.Config.Builder的setPageSize参数
        Log.e("Bill", "loadBefore:" + params.key + "|" + params.requestedLoadSize);
    }

    @NonNull
    @Override
    public Integer getKey(@NonNull StudentEntity item) {
        return item.id;
    }
}
