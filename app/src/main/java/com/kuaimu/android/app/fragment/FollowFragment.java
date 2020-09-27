package com.kuaimu.android.app.fragment;

import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.recyclerview.widget.GridLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.baselibrary.utils.CommonUtil;
import com.kuaimu.android.app.R;
import com.kuaimu.android.app.activity.WorkDetailActivity;
import com.kuaimu.android.app.adapter.FollowListAdapter;
import com.kuaimu.android.app.databinding.FragmentFollowBinding;
import com.kuaimu.android.app.model.BaseData;
import com.kuaimu.android.app.model.FollowData;
import com.kuaimu.android.app.view.GridItemDecoration;
import com.kuaimu.android.app.view.OnClickListener;
import com.okhttp.SendRequest;
import com.okhttp.callbacks.GenericsCallback;
import com.okhttp.sample_okhttp.JsonGenericsSerializator;

import okhttp3.Call;

public class FollowFragment extends BaseFragment {

    private FragmentFollowBinding binding;
    private FollowListAdapter adapter;
    private FollowData followData;
    private int uid = 0;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_follow, container, false);
        setStatusBarHeight(binding.getRoot());
        setStatusBarDarkTheme(true);

        adapter = new FollowListAdapter(getActivity());
        binding.recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        GridItemDecoration.Builder builder = new GridItemDecoration.Builder(getActivity());
        builder.color(R.color.transparent);
        builder.size(CommonUtil.dip2px(getActivity(), 10));
        binding.recyclerView.addItemDecoration(new GridItemDecoration(builder));
        binding.recyclerView.setAdapter(adapter);
        adapter.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view, Object object) {
                if (followData != null) {
                    Intent intent = new Intent(getActivity(), WorkDetailActivity.class);
                    BaseData baseData = new BaseData();
                    baseData.setData(followData.getData().getData());
                    intent.putExtra("baseData", baseData);
                    intent.putExtra("position", (int) object);
                    getActivity().startActivity(intent);
                }
            }

            @Override
            public void onLongClick(View view, Object object) {

            }
        });

        binding.swipeRefreshLayout.setColorSchemeColors(getResources().getColor(R.color.colorPrimary));
        binding.swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                initData();
            }
        });
        initData();


        return binding.getRoot();
    }

    @Override
    public void onResume() {
        super.onResume();
        if (getUid() != 0 && uid != getUid()) {
            initData();
        }
    }

    private void initData() {
        uid = getUid();
        binding.swipeRefreshLayout.setRefreshing(true);
        SendRequest.homeSelfAttention(getUid(), new GenericsCallback<FollowData>(new JsonGenericsSerializator()) {
            @Override
            public void onError(Call call, Exception e, int id) {
                binding.swipeRefreshLayout.setRefreshing(false);
            }

            @Override
            public void onResponse(FollowData response, int id) {
                binding.swipeRefreshLayout.setRefreshing(false);
                if (response.getCode() == 200 && response.getData() != null && response.getData().getData() != null) {
                    followData = response;
                    adapter.refreshData(response.getData().getData());
                } else {

                }
            }

        });
    }
}
