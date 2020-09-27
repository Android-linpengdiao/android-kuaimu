package com.kuaimu.android.app.fragment;

import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.baselibrary.MessageBus;
import com.baselibrary.utils.CommonUtil;
import com.baselibrary.utils.SharedPreferencesUtils;
import com.baselibrary.utils.ToastUtils;
import com.kuaimu.android.app.R;
import com.kuaimu.android.app.activity.WorkDetailActivity;
import com.kuaimu.android.app.adapter.WorkAdapter;
import com.kuaimu.android.app.databinding.FragmentSearchResultBinding;
import com.kuaimu.android.app.model.BaseData;
import com.kuaimu.android.app.model.WorkData;
import com.kuaimu.android.app.view.GridItemDecoration;
import com.kuaimu.android.app.view.OnClickListener;
import com.okhttp.SendRequest;
import com.okhttp.callbacks.GenericsCallback;
import com.okhttp.sample_okhttp.JsonGenericsSerializator;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;

import okhttp3.Call;

public class SearchResultFragment extends BaseFragment implements View.OnClickListener {

    private static final String TAG = "SearchResultFragment";
    private FragmentSearchResultBinding binding;
    private WorkAdapter adapter;
    private WorkData workData;
    private String content;
    private String busLabel;
    private String personLabel;

    public static SearchResultFragment newInstance(String busLabel, String personLabel) {
        SearchResultFragment fragment = new SearchResultFragment();
        Bundle args = new Bundle();
        args.putString("busLabel", busLabel);
        args.putString("personLabel", personLabel);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            busLabel = getArguments().getString("busLabel");
            personLabel = getArguments().getString("personLabel");
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_search_result, container, false);
        setStatusBarHeight(binding.getRoot());
        setStatusBarDarkTheme(true);
        Log.i(TAG, "onCreateView: " + busLabel);
        adapter = new WorkAdapter(getActivity());
        binding.recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        GridItemDecoration.Builder builder = new GridItemDecoration.Builder(getActivity());
        builder.color(R.color.transparent);
        builder.size(CommonUtil.dip2px(getActivity(), 10));
        binding.recyclerView.addItemDecoration(new GridItemDecoration(builder));
        binding.recyclerView.setAdapter(adapter);
        adapter.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view, Object object) {
                if (workData != null) {
                    Log.i(TAG, "onClick: ");
                    Intent intent = new Intent(getActivity(), WorkDetailActivity.class);
                    BaseData baseData = new BaseData();
                    baseData.setData(workData.getData().getData());
                    intent.putExtra("baseData", baseData);
                    intent.putExtra("position", (int) object);
                    getActivity().startActivity(intent);
                }
            }

            @Override
            public void onLongClick(View view, Object object) {

            }
        });

        EventBus.getDefault().register(this);

        return binding.getRoot();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getMainMessage(MessageBus messageBus) {
        if (messageBus.getCodeType().equals(messageBus.msgId_searchContent)) {
            content = String.valueOf(messageBus.getParam1());
            personLabel = String.valueOf(messageBus.getParam2());
            searchWork();
        }
        if (messageBus.getCodeType().equals(messageBus.msgId_searchCancel) && adapter != null) {
            adapter.refreshData(new ArrayList<>());
            binding.emptyView.setVisibility(View.GONE);
        } else if (messageBus.getCodeType().equals(messageBus.msgId_searchLabel)) {
            personLabel = String.valueOf(messageBus.getMessage());
            searchWork();
        }

    }

    private void searchWork() {
        binding.emptyView.setVisibility(View.GONE);
        SendRequest.search(getUid(), content, busLabel, personLabel, SharedPreferencesUtils.getInstance().getCity(), new GenericsCallback<WorkData>(new JsonGenericsSerializator()) {
            @Override
            public void onError(Call call, Exception e, int id) {

            }

            @Override
            public void onResponse(WorkData response, int id) {
                if (response.getCode() == 200) {
                    workData = response;
                    adapter.refreshData(response.getData().getData());
                    binding.emptyView.setVisibility(response.getData().getData().size()>0?View.GONE:View.VISIBLE);
                } else {
                    ToastUtils.showShort(getActivity(), response.getMsg());
                }
            }

        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

        }
    }

    @Override
    public void onDestroyView() {
        EventBus.getDefault().unregister(this);
        super.onDestroyView();
    }
}
