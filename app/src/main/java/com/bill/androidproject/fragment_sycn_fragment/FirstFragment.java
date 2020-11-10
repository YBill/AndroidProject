package com.bill.androidproject.fragment_sycn_fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.bill.androidproject.R;

/**
 * author : Bill
 * date : 2020/11/5
 * description :
 */
public class FirstFragment extends Fragment {

    private AppCompatTextView textView;
    private AppCompatButton button;

    private MyViewModel viewModel;
    private int num;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_first, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        textView = view.findViewById(R.id.tv_text_1);
        button = view.findViewById(R.id.btn_1);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewModel.update(++num);
            }
        });

        viewModel = ViewModelProviders.of(getActivity()).get(MyViewModel.class);
        viewModel.getLiveData().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                num = integer;
                Log.e("Bill", "FirstFragment:" + integer);

                textView.setText(String.valueOf(num));
            }
        });

    }
}
