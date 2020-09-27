package com.kuaimu.android.app.adapter;

import android.os.Parcelable;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.kuaimu.android.app.fragment.MineLikeFragment;
import com.kuaimu.android.app.fragment.MineWorkFragment;

import java.util.ArrayList;
import java.util.List;

public class MinePagerAdapter extends FragmentPagerAdapter {

    private List<String> titles = new ArrayList<>();
    private int uid;

    public void addTitle(String title) {
        titles.add(title);
    }

    public MinePagerAdapter(FragmentManager fm, int uid) {
        super(fm);
        this.uid = uid;
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return MineWorkFragment.newInstance(uid);
        } else if (position == 1) {
            return MineLikeFragment.newInstance(uid);
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
}