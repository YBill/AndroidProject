package com.bill.androidproject;

import android.app.Application;
import android.content.Context;

/**
 * author : Bill
 * date : 2020/7/13
 * description :
 */
public class MyApplication extends Application {

    public static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();

        mContext = getApplicationContext();
    }
}
