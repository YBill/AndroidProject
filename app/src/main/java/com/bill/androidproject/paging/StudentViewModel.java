package com.bill.androidproject.paging;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.paging.DataSource;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;

import com.bill.androidproject.paging.db.StudentEntity;

/**
 * author : Bill
 * date : 2020/7/15
 * description :
 */
public class StudentViewModel extends AndroidViewModel {

    private DataSource<Integer, StudentEntity> dataSource;

    public StudentViewModel(@NonNull Application application) {
        super(application);
    }

    private DataSource.Factory<Integer, StudentEntity> factory = new DataSource.Factory<Integer, StudentEntity>() {
        @Override
        public DataSource<Integer, StudentEntity> create() {
//            dataSource = new StudentDataSource();
//            dataSource = new StudentDataSource2();
            dataSource = new StudentDataSource3();
            return dataSource;
        }
    };

    public LiveData<PagedList<StudentEntity>> getAllStudents() {

        int pageSize = 10;

        PagedList.Config pageListConfig = new PagedList.Config.Builder()
                .setEnablePlaceholders(false) // 配置是否启动PlaceHolders
                .setInitialLoadSizeHint(pageSize * 2) // 初始化数量
                .setPageSize(pageSize) // 配置分页加载的数量
//                .setPrefetchDistance(8)  //预加载距离：还剩x个就要滑到底了，就进行预加载
                .build();

        // 可以直接绑定Room数据库
//        DataSource.Factory<Integer, StudentEntity> factory = DbHelper.getInstance().getStudentDao().getAllStudents();
        return new LivePagedListBuilder<>(factory, pageListConfig).build();

    }

    public void refresh() {
        dataSource.invalidate();
    }
}
