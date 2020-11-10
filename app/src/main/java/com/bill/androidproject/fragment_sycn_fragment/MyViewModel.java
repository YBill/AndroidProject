package com.bill.androidproject.fragment_sycn_fragment;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

/**
 * author : Bill
 * date : 2020/11/6
 * description :
 */
public class MyViewModel extends ViewModel {

    private MutableLiveData<Integer> liveData = new MutableLiveData<>();

    public MyViewModel() {

    }

    public LiveData<Integer> getLiveData() {
        return liveData;
    }

    public void update(Integer num) {
        liveData.postValue(num);
    }

}
