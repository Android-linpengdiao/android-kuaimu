package com.kuaimu.android.app.fragment;

import androidx.databinding.DataBindingUtil;

import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.baselibrary.utils.CommonUtil;
import com.baselibrary.utils.ToastUtils;
import com.kuaimu.android.app.NavData;
import com.kuaimu.android.app.R;
import com.kuaimu.android.app.adapter.PagerAdapter;
import com.kuaimu.android.app.databinding.FragmentDiscoverBinding;
import com.okhttp.SendRequest;
import com.okhttp.callbacks.GenericsCallback;
import com.okhttp.sample_okhttp.JsonGenericsSerializator;

import androidx.viewpager.widget.ViewPager;
import okhttp3.Call;

public class DiscoverFragment extends BaseFragment implements View.OnClickListener, ViewPager.OnPageChangeListener {
    private static final String TAG = "DiscoverFragment";
    private FragmentDiscoverBinding binding;
    private PagerAdapter pagerAdapter;
    private NavData navData;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_discover, container, false);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) binding.viewLayout.getLayoutParams();
            layoutParams.topMargin = CommonUtil.getStatusBarHeight(getActivity()) + CommonUtil.dip2px(getActivity(), 76);
        }
        binding.viewPager.addOnPageChangeListener(this);
        SendRequest.category(new GenericsCallback<NavData>(new JsonGenericsSerializator()) {
            @Override
            public void onError(Call call, Exception e, int id) {

            }

            @Override
            public void onResponse(NavData response, int id) {
                navData = response;
                if (response.getCode() == 200 && response.getData() != null) {
                    pagerAdapter = new PagerAdapter(getChildFragmentManager());
                    for (int i = 0; i < response.getData().size(); i++) {
                        pagerAdapter.addFragment(response.getData().get(i).getName(), HomeItemFragment.newInstance(response.getData().get(i).getId()));
                    }
                    binding.viewPager.setAdapter(pagerAdapter);
                    binding.viewPager.setOffscreenPageLimit(response.getData().size());
                    binding.viewPager.setCurrentItem(0);
                    binding.tabLayout.setupWithViewPager(binding.viewPager);
                } else {
                    ToastUtils.showShort(getActivity(), response.getMsg());
                }
            }

        });

        return binding.getRoot();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

        }
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.i(TAG, "onResume: ");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.i(TAG, "onPause: ");
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        Log.i(TAG, "onPageSelected: " + position);
        if (navData.getCode() == 200 && navData.getData() != null) {

        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
