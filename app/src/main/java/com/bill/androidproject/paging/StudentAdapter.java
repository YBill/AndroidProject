package com.bill.androidproject.paging;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.bill.androidproject.R;
import com.bill.androidproject.paging.db.StudentEntity;

/**
 * author : Bill
 * date : 2020/7/15
 * description :
 */
public class StudentAdapter extends PagedListAdapter<StudentEntity, StudentAdapter.StudentViewHolder> {

    private static final DiffUtil.ItemCallback<StudentEntity> DIFF_CALLBACK = new DiffUtil.ItemCallback<StudentEntity>() {
        @Override
        public boolean areItemsTheSame(StudentEntity oldItem, StudentEntity newItem) {
            return oldItem.id == newItem.id;
        }

        @Override
        public boolean areContentsTheSame(StudentEntity oldItem, StudentEntity newItem) {
            return oldItem.equals(newItem);
        }
    };

    protected StudentAdapter() {
        super(DIFF_CALLBACK);
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

    class StudentViewHolder extends RecyclerView.ViewHolder {

        private AppCompatTextView titleTv;
        private AppCompatTextView contentTv;

        public StudentViewHolder(@NonNull View itemView) {
            super(itemView);
            titleTv = itemView.findViewById(R.id.tv_title);
            contentTv = itemView.findViewById(R.id.tv_content);

        }

        private void update(final int position) {
            final StudentEntity bean = getItem(position);

            titleTv.setText(String.valueOf(bean.id));
            contentTv.setText(bean.name);

        }

    }

}
