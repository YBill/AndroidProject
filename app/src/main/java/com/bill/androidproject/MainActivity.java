package com.bill.androidproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.bill.androidproject.fragment_sycn_fragment.FragmentSycnActivity;
import com.bill.androidproject.lv.LVTestActivity;
import com.bill.androidproject.paging.PagingTestActivity;
import com.bill.androidproject.room.RoomTestActivity;
import com.bill.androidproject.vp2.ViewPager2Activity;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void handleVM(View view) {
        startActivity(new Intent(this, LVTestActivity.class));
    }

    public void handleRoom(View view) {
        startActivity(new Intent(this, RoomTestActivity.class));
    }

    public void handlePaging(View view) {
        startActivity(new Intent(this, PagingTestActivity.class));
    }

    public void handleVP2(View view) {
        startActivity(new Intent(this, ViewPager2Activity.class));
    }

    public void handleFs(View view) {
        startActivity(new Intent(this, FragmentSycnActivity.class));
    }

    public void handleDataBinding(View view) {
    }

    public void handleDataStore(View view) {
    }
}
