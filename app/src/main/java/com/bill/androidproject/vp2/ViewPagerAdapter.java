package com.bill.androidproject.vp2;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

import com.bill.androidproject.R;

import java.util.ArrayList;
import java.util.List;

/**
 * author : Bill
 * date : 2020/11/5
 * description :
 */
public class ViewPagerAdapter extends RecyclerView.Adapter<ViewPagerAdapter.MyViewHolder> {

    private List<String> mDataList = new ArrayList<>();

    public void setData(List<String> list) {
        mDataList.clear();
        mDataList.addAll(list);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View textView = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_vp_item, parent, false);
        return new MyViewHolder(textView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.update(position);
    }

    @Override
    public int getItemCount() {
        return mDataList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private AppCompatTextView textView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.tv_title);
        }

        private void update(int position) {
            String text = mDataList.get(position);
            textView.setText(text);

            if (position == 0) {
                itemView.setBackgroundColor(Color.parseColor("#7B68EE"));
            } else if (position == 1) {
                itemView.setBackgroundColor(Color.parseColor("#40E0D0"));
            } else {
                itemView.setBackgroundColor(Color.parseColor("#FFA07A"));
            }

        }

    }

}
