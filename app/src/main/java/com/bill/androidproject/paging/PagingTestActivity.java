package com.bill.androidproject.paging;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.paging.PagedList;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bill.androidproject.R;
import com.bill.androidproject.paging.db.StudentEntity;

public class PagingTestActivity extends AppCompatActivity {

    private RecyclerView mRv;
    private StudentAdapter myAdapter;
    private StudentViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paging_test);

        mRv = findViewById(R.id.rv_list);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRv.setLayoutManager(layoutManager);
        myAdapter = new StudentAdapter();
        mRv.setAdapter(myAdapter);

        viewModel = ViewModelProviders.of(this).get(StudentViewModel.class);
        observeViewModel(viewModel);

    }

    private void observeViewModel(StudentViewModel viewModel) {
        viewModel.getAllStudents().observe(this, new Observer<PagedList<StudentEntity>>() {
            @Override
            public void onChanged(final PagedList<StudentEntity> studentEntities) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        myAdapter.submitList(studentEntities);
                    }
                });
            }
        });
    }

}
