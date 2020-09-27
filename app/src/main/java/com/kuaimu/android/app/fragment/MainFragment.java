package com.kuaimu.android.app.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.baselibrary.utils.GlideLoader;
import com.baselibrary.utils.StatusBarUtil;
import com.kuaimu.android.app.R;
import com.kuaimu.android.app.activity.SearchActivity;
import com.kuaimu.android.app.adapter.PagerAdapter;
import com.kuaimu.android.app.databinding.FragmentHomeBinding;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.viewpager.widget.ViewPager;

public class MainFragment extends BaseFragment implements View.OnClickListener, ViewPager.OnPageChangeListener {
    private FragmentHomeBinding binding;
    private PagerAdapter mainHomePagerAdapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false);
        setStatusBarHeight(binding.getRoot(), getResources().getColor(R.color.white));

        binding.search.setOnClickListener(this);

        mainHomePagerAdapter = new PagerAdapter(getChildFragmentManager());
        mainHomePagerAdapter.addFragment("同城", new TongchengFragment());
        mainHomePagerAdapter.addFragment("关注", new RecommendFragment());
        mainHomePagerAdapter.addFragment("一线", new DiscoverFragment());
        binding.viewPager.setAdapter(mainHomePagerAdapter);
        binding.viewPager.setOffscreenPageLimit(3);
        binding.viewPager.setCurrentItem(0);
        binding.tabLayout.setupWithViewPager(binding.viewPager);
        binding.viewPager.addOnPageChangeListener(this);

        return binding.getRoot();
    }


    @Override
    public void onResume() {
        super.onResume();
//        if (getUid() > 0) {
//            GlideLoader.LoderCircleImage(getActivity(), getUserInfo().getData().getAvatar(), binding.userIconView);
//        } else {
//            GlideLoader.LoderCircleImage(getActivity(), "", binding.userIconView);
//        }
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
//        if (getUid() > 0) {
//            GlideLoader.LoderCircleImage(getActivity(), getUserInfo().getData().getAvatar(), binding.userIconView);
//        } else {
//            GlideLoader.LoderCircleImage(getActivity(), "", binding.userIconView);
//        }
    }

    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()) {
            case R.id.search:
                intent = new Intent(getActivity(), SearchActivity.class);
                startActivity(intent);
                break;
        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        binding.statusBar.setBackgroundColor(position == 2 ? Color.TRANSPARENT : getResources().getColor(R.color.white));
        binding.topView.setBackgroundColor(position == 2 ? getResources().getColor(R.color.transparent) : getResources().getColor(R.color.white));

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
