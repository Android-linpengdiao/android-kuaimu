package com.kuaimu.android.app.fragment;

import android.content.Intent;

import androidx.databinding.DataBindingUtil;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.baselibrary.utils.GlideLoader;
import com.kuaimu.android.app.R;
import com.kuaimu.android.app.activity.SearchActivity;
import com.kuaimu.android.app.adapter.PagerAdapter;
import com.kuaimu.android.app.databinding.FragmentHomeBinding;

public class HomeFragment extends BaseFragment implements View.OnClickListener {
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
        setStatusBarHeight(binding.getRoot());
        setStatusBarDarkTheme(true);

        binding.searchImageView.setOnClickListener(this);

        mainHomePagerAdapter = new PagerAdapter(getChildFragmentManager());
        mainHomePagerAdapter.addFragment("推荐", new RecommendFragment());
        mainHomePagerAdapter.addFragment("同城", new TongchengFragment());
        mainHomePagerAdapter.addFragment("发现", new DiscoverFragment());
        binding.viewPager.setAdapter(mainHomePagerAdapter);
        binding.viewPager.setOffscreenPageLimit(3);
        binding.viewPager.setCurrentItem(0);
        binding.tabLayout.setupWithViewPager(binding.viewPager);

        return binding.getRoot();
    }


    @Override
    public void onResume() {
        super.onResume();
        if (getUid() > 0) {
            GlideLoader.LoderCircleImage(getActivity(), getUserInfo().getData().getAvatar(), binding.userIconView);
        } else {
            GlideLoader.LoderCircleImage(getActivity(), "", binding.userIconView);
        }
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (getUid() > 0) {
            GlideLoader.LoderCircleImage(getActivity(), getUserInfo().getData().getAvatar(), binding.userIconView);
        } else {
            GlideLoader.LoderCircleImage(getActivity(), "", binding.userIconView);
        }
    }

    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()) {
            case R.id.searchImageView:
                intent = new Intent(getActivity(), SearchActivity.class);
                startActivity(intent);
                break;
        }
    }
}
