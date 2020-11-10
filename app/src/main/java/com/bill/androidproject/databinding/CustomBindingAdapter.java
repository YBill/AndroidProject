package com.bill.androidproject.databinding;

import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

/**
 * author : Bill
 * date : 2020/11/10
 * description :
 */
public class CustomBindingAdapter {

    @BindingAdapter({"imageRes"})
    public static void loadImage(ImageView imageView, int res) {
        imageView.setImageResource(res);
    }

}
