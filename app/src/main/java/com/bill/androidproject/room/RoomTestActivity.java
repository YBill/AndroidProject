package com.bill.androidproject.room;

import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatTextView;

import com.bill.androidproject.R;

import java.util.List;

public class RoomTestActivity extends AppCompatActivity {

    private AppCompatTextView contentTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room_test);

        contentTv = findViewById(R.id.tv_content);
        contentTv.setMovementMethod(ScrollingMovementMethod.getInstance());

        select();
    }

    private void select() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                final List<UserEntity> list = DbHelper.getInstance().getUserDao().getAllUsers();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        contentTv.setText("");

                        if (list != null) {
                            for (UserEntity entity : list) {
                                contentTv.append(entity.id + " | " + entity.userName);
                                contentTv.append("\n");
                            }
                        }
                    }
                });
            }
        }).start();

    }

    private int index = 0;

    public void handleInsert(View view) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                index++;
                UserEntity entity = new UserEntity();
                entity.id = "b_" + index;
                entity.userName = "Bill_" + index;
                DbHelper.getInstance().getUserDao().insertUser(entity);

                select();
            }
        }).start();

    }
}
