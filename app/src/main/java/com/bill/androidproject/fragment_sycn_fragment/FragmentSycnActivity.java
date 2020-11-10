package com.bill.androidproject.fragment_sycn_fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.viewpager2.widget.ViewPager2;

import com.bill.androidproject.R;

import java.util.ArrayList;
import java.util.List;

/**
 * author : Bill
 * date : 2020/11/6
 * description : Activity和Fragment中数据同步
 */
public class FragmentSycnActivity extends AppCompatActivity {

    private ViewPager2 mViewPager;
    private ViewPagerFragmentStateAdapter mAdapter;

    private AppCompatTextView textView;
    private AppCompatButton button;

    private MyViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_sycn);
        mViewPager = findViewById(R.id.vp_content);

        mAdapter = new ViewPagerFragmentStateAdapter(this);
        mViewPager.setAdapter(mAdapter);


        List<Fragment> list = new ArrayList<>();
        list.add(new FirstFragment());
        list.add(new SecondFragment());
        mAdapter.setFragments(list);

        textView = findViewById(R.id.tv_text);
        button = findViewById(R.id.btn);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewModel.update(0);
            }
        });

        viewModel = ViewModelProviders.of(this).get(MyViewModel.class);
        viewModel.getLiveData().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                Log.e("Bill", "Activity:" + integer);

                textView.setText(String.valueOf(integer));
            }
        });

    }

}
