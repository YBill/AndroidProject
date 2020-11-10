package com.bill.androidproject.paging;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.paging.PositionalDataSource;

import com.bill.androidproject.paging.db.StudentEntity;
import com.bill.androidproject.room.DbHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * author : Bill
 * date : 2020/7/16
 * description : 根据RecyclerView的下面来加载的
 */
public class StudentDataSource3 extends PositionalDataSource<StudentEntity> {

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
    public void loadInitial(@NonNull final LoadInitialParams params, @NonNull final LoadInitialCallback<StudentEntity> callback) {
        Log.e("Bill", "loadInitial:" + params.requestedStartPosition + "|" + params.requestedLoadSize + "|" + params.pageSize);

        new Thread(new Runnable() {
            @Override
            public void run() {
                int P = 30;

                List<StudentEntity> list = DbHelper.getInstance().getStudentDao().getAllStudents(P, params.requestedLoadSize);
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


                // loadInitial加载数据显示的位置，如果设置为30，则当前的数据前面则还有30条数据，
                // loadInitial回调后会调用loadRange，往下拉会继续回调loadRange，直到30条数据加载完（
                // loadRange中loadSize的是setPageSize的值10，startPosition是position-loadSize，后面会依次减loadSize，一直到最后）
                // 上拉会加载后面数据，调用loadRange，(loadSize的是setPageSize的值10，startPosition最开始是position+list.size(),
                // 后面会依次加loadSize，一直到最后)
                int position = P;
                callback.onResult(list, position);
            }
        }).start();
    }

    @Override
    public void loadRange(@NonNull final LoadRangeParams params, @NonNull final LoadRangeCallback<StudentEntity> callback) {
        Log.e("Bill", "loadRange:" + params.startPosition + "|" + params.loadSize);

        new Thread(new Runnable() {
            @Override
            public void run() {
                List<StudentEntity> list = DbHelper.getInstance().getStudentDao().getAllStudents(params.startPosition, params.loadSize);

                Log.d("Bill", "getData:" + (list == null ? "null" : list.size()));

                callback.onResult(list);
            }
        }).start();

    }
}
