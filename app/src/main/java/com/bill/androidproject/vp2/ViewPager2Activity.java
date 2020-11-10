package com.bill.androidproject.vp2;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import com.bill.androidproject.R;

import java.util.ArrayList;
import java.util.List;

public class ViewPager2Activity extends AppCompatActivity {

    private ViewPager2 mViewPager;
    private ViewPagerAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pager2);
        mViewPager = findViewById(R.id.vp_content);

        mViewPager.setOrientation(ViewPager2.ORIENTATION_VERTICAL);

        mAdapter = new ViewPagerAdapter();
        mViewPager.setAdapter(mAdapter);
        mAdapter.setData(getData());

    }

    private List<String> getData() {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            list.add(String.valueOf(i));
        }
        return list;
    }
}
