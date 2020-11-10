package com.bill.androidproject.fragment_sycn_fragment;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * author : Bill
 * date : 2020/11/5
 * description :
 */
public class ViewPagerFragmentStateAdapter extends FragmentStateAdapter {

    private List<Fragment> mFragments = new ArrayList<>();

    public ViewPagerFragmentStateAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    public void setFragments(List<Fragment> fragments) {
        this.mFragments.clear();
        mFragments.addAll(fragments);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        return mFragments.get(position);
    }

    @Override
    public int getItemCount() {
        return mFragments.size();
    }
}
