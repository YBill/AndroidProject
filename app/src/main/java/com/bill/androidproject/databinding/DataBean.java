package com.bill.androidproject.databinding;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.bill.androidproject.BR;

/**
 * author : Bill
 * date : 2020/11/10
 * description : 如果需要动态修改某一个值，则bean继承BaseObservable，
 * 属性xxx用@Bindable注解，通过notifyPropertyChanged(BR.xxx)可以更新某一个数据
 */
public class DataBean extends BaseObservable {

    public String name;
    @Bindable
    public int age;
    public int sex;
    public int avatar;

    // 调用可以更新布局中age的值
    public void changeAge(int age) {
        this.age = age;
        notifyPropertyChanged(BR.age);
    }

}
