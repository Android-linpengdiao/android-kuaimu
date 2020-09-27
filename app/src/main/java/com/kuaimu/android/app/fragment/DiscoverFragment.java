package com.kuaimu.android.app.fragment;

import androidx.databinding.DataBindingUtil;

import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
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

import okhttp3.Call;

public class DiscoverFragment extends BaseFragment implements View.OnClickListener{

    private FragmentDiscoverBinding binding;
    private PagerAdapter pagerAdapter;


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

        SendRequest.category(new GenericsCallback<NavData>(new JsonGenericsSerializator()) {
            @Override
            public void onError(Call call, Exception e, int id) {

            }

            @Override
            public void onResponse(NavData response, int id) {
                if (response.getCode() == 200 && response.getData() != null) {
                    pagerAdapter = new PagerAdapter(getChildFragmentManager());
                    for (int i = 0; i < response.getData().size(); i++) {
                        pagerAdapter.addFragment(response.getData().get(i).getName(), HomeItemFragment.newInstance(response.getData().get(i).getName()));
                    }
                    binding.viewPager.setAdapter(pagerAdapter);
                    binding.viewPager.setOffscreenPageLimit(1);
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
}