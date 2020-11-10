package com.bill.androidproject.databinding;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.bill.androidproject.R;

public class DataBindingActivity extends AppCompatActivity {

    private ActivityDataBindingBinding dataBinding;

    private int money;
    private DataBean user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        dataBinding = DataBindingUtil.inflate(LayoutInflater.from(this),
//                R.layout.activity_data_binding, null, false);
//        setContentView(dataBinding.getRoot());

        dataBinding = DataBindingUtil.setContentView(this, R.layout.activity_data_binding);

        dataBinding.setPresenter(new Presenter());

        user = new DataBean();
        user.avatar = R.mipmap.ic_launcher;
        user.age = 28;
        user.sex = 1;
        dataBinding.setUser(user);
    }

    public class Presenter {
        public void itemClick(View view, DataBean bean) {
            switch (view.getId()) {
                case R.id.iv_avatar:
                    money++;
                    dataBinding.tvMoney.setText(String.valueOf(money));
                    break;
            }

        }

    }

}
