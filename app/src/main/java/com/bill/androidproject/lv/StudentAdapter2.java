package com.bill.androidproject.lv;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

import com.bill.androidproject.R;
import com.bill.androidproject.paging.db.StudentEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * author : Bill
 * date : 2020/7/17
 * description :
 */
public class StudentAdapter2 extends RecyclerView.Adapter<StudentAdapter2.StudentViewHolder> {

    private Context mContext;
    private List<StudentEntity> mDataList = new ArrayList<>();

    public StudentAdapter2(Context context) {
        this.mContext = context;
    }

    public void setDataList(List<StudentEntity> dataList) {
        this.mDataList.clear();
        this.mDataList.addAll(dataList);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public StudentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_item, parent, false);
        return new StudentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StudentViewHolder holder, int position) {
        holder.update(position);
    }

    @Override
    public int getItemCount() {
        return mDataList.size();
    }

    class StudentViewHolder extends RecyclerView.ViewHolder {

        private AppCompatTextView titleTv;
        private AppCompatTextView contentTv;

        public StudentViewHolder(@NonNull View itemView) {
            super(itemView);
            titleTv = itemView.findViewById(R.id.tv_title);
            contentTv = itemView.findViewById(R.id.tv_content);

        }

        private void update(final int position) {
            final StudentEntity bean = mDataList.get(position);

            titleTv.setText(String.valueOf(bean.id));
            contentTv.setText(bean.name);

        }

    }

}
