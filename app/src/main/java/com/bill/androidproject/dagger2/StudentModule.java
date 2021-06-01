package com.bill.androidproject.dagger2;

import dagger.Module;
import dagger.Provides;

/**
 * author : Bill
 * date : 2021/5/31
 * description :
 */
@Module
public class StudentModule {

    private Dagger2Activity mActivity;

    public StudentModule(Dagger2Activity activity) {
        this.mActivity = activity;
    }

    @Provides
    Student provideStudent() {
        return new Student();
    }

}
