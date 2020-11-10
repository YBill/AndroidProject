package com.bill.androidproject.lv;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bill.androidproject.R;
import com.bill.androidproject.paging.db.StudentEntity;

import java.util.List;

public class LVTestActivity extends AppCompatActivity {

    private RecyclerView mRv;
    private StudentAdapter2 myAdapter;
    private StudentViewModel2 viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_l_v_test);

        mRv = findViewById(R.id.rv_list);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRv.setLayoutManager(layoutManager);
        myAdapter = new StudentAdapter2(this);
        mRv.setAdapter(myAdapter);

        viewModel = ViewModelProviders.of(this).get(StudentViewModel2.class);
        observeViewModel(viewModel);

        viewModel.getData(1);
    }


    private void observeViewModel(StudentViewModel2 viewModel) {
        viewModel.getStudentObservable().observe(this, new Observer<List<StudentEntity>>() {
            @Override
            public void onChanged(List<StudentEntity> studentEntities) {
                myAdapter.setDataList(studentEntities);
            }
        });

        viewModel.getErrorLiveData().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {

            }
        });
    }

}
