package com.bill.androidproject.lv;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.arch.core.util.Function;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Transformations;

import com.bill.androidproject.paging.db.StudentEntity;

import java.util.List;

/**
 * author : Bill
 * date : 2020/7/17
 * description :
 */
public class StudentViewModel2 extends AndroidViewModel {

    private StudentRepository studentRepository;

    private final LiveData<List<StudentEntity>> studentObservable;

    public StudentViewModel2(@NonNull Application application) {
        super(application);
        studentRepository = new StudentRepository();

        LiveData<List<StudentEntity>> liveData = studentRepository.getLiveData();

        // 处理网络返回的数据
        studentObservable = Transformations.map(liveData, new Function<List<StudentEntity>, List<StudentEntity>>() {
            @Override
            public List<StudentEntity> apply(List<StudentEntity> input) {
                for (StudentEntity studentEntity : input) {
                    studentEntity.name = "Cover " + studentEntity.name;
                }

                return input;
            }
        });
    }

    public LiveData<List<StudentEntity>> getStudentObservable() {
        return studentObservable;
    }

    public LiveData<Integer> getErrorLiveData() {
        return studentRepository.getLiveErrorData();
    }

    public void getData(int page) {
        studentRepository.getData(page);
    }
}
