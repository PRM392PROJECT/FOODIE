package com.example.foodie.customview;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import java.util.List;

public class CollectionPagerAdapter extends FragmentStateAdapter {
    private List<Fragment> fragmentList;

    public CollectionPagerAdapter(@NonNull FragmentActivity fragmentActivity, List<Fragment> fragmentList) {
        super(fragmentActivity);
        this.fragmentList = fragmentList;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getItemCount() {
        return fragmentList.size();
    }

    @Override
    public long getItemId(int position) {
        return fragmentList.get(position).hashCode(); // Cung cấp ID duy nhất cho mỗi fragment
    }

    @Override
    public boolean containsItem(long itemId) {
        for (Fragment fragment : fragmentList) {
            if (fragment.hashCode() == itemId) {
                return true;
            }
        }
        return false;
    }
    public void updateFragments(List<Fragment> newFragments) {
        fragmentList.clear();
        fragmentList.addAll(newFragments);
        notifyDataSetChanged();
    }
}

