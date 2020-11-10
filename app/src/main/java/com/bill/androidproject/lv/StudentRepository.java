package com.bill.androidproject.lv;

import androidx.lifecycle.MutableLiveData;

import com.bill.androidproject.paging.db.StudentEntity;
import com.bill.androidproject.room.DbHelper;

import java.util.List;

/**
 * author : Bill
 * date : 2020/7/17
 * description :
 */
public class StudentRepository {

    private MutableLiveData<List<StudentEntity>> liveData;
    private MutableLiveData<Integer> liveErrorData;

    public StudentRepository() {
        liveData = new MutableLiveData<>();
        liveErrorData = new MutableLiveData<>();
    }

    public MutableLiveData<List<StudentEntity>> getLiveData() {
        return liveData;
    }

    public MutableLiveData<Integer> getLiveErrorData() {
        return liveErrorData;
    }

    public void getData(int page) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    List<StudentEntity> list = DbHelper.getInstance().getStudentDao().getAllStudents(0, 100);
                    liveData.postValue(list);
                } catch (Exception e) {
                    liveErrorData.postValue(0);
                    e.printStackTrace();
                }
            }
        }).start();
    }

}
