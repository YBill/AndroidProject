package com.bill.androidproject.dagger2;

import dagger.Component;

/**
 * author : Bill
 * date : 2021/5/31
 * description :
 */
@Component(modules = StudentModule.class)
public interface StudentComponent {

    void inject(Dagger2Activity activity);

}
