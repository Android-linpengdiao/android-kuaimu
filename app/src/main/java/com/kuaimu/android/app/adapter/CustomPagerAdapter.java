package com.kuaimu.android.app.adapter;

import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class CustomPagerAdapter extends FragmentPagerAdapter {

    private List<String> titles = new ArrayList<>();
    private List<Fragment> fragments = new ArrayList<>();

    public CustomPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        if (fragments != null && position <= fragments.size()) {
            return fragments.get(position);
        }
        return null;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        if (titles != null && position <= titles.size()) {
            return titles.get(position);
        }
        return super.getPageTitle(position);
    }

    // 动态设置我们标题的方法
    public void setPageTitle(int position, String title) {
        if (position >= 0 && position < titles.size()) {
            titles.set(position, title);
            notifyDataSetChanged();
        }
    }

    @Override
    public int getCount() {
        return titles.size();
    }

    @Override
    public void restoreState(Parcelable state, ClassLoader loader) {
        super.restoreState(state, loader);
    }

    public void addTitle(String title) {
        titles.add(title);
    }

    public void addFragment(Fragment fragment) {
        fragments.add(fragment);
    }
}