package com.kuaimu.android.app.fragment;

import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.baselibrary.Constants;
import com.baselibrary.utils.CommonUtil;
import com.baselibrary.utils.SharedPreferencesUtils;
import com.baselibrary.utils.ToastUtils;
import com.kuaimu.android.app.R;
import com.kuaimu.android.app.activity.WorkDetailActivity;
import com.kuaimu.android.app.adapter.RecommendAdapter;
import com.kuaimu.android.app.databinding.FragmentRecommendBinding;
import com.kuaimu.android.app.model.BaseData;
import com.kuaimu.android.app.model.RecommendData;
import com.kuaimu.android.app.utils.SpringViewNewFooter;
import com.kuaimu.android.app.utils.SpringViewNewHeader;
import com.kuaimu.android.app.view.GridItemDecoration;
import com.kuaimu.android.app.view.OnClickListener;
import com.liaoinstan.springview.widget.SpringView;
import com.okhttp.SendRequest;
import com.okhttp.callbacks.GenericsCallback;
import com.okhttp.sample_okhttp.JsonGenericsSerializator;

import okhttp3.Call;

public class RecommendFragment extends BaseFragment {

    private FragmentRecommendBinding binding;
    private RecommendAdapter adapter;
    private RecommendData recommendData;
    private boolean isRefresh = true;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_recommend, container, false);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) binding.viewLayout.getLayoutParams();
            layoutParams.topMargin = CommonUtil.getStatusBarHeight(getActivity()) + CommonUtil.dip2px(getActivity(), 60);
        }

        adapter = new RecommendAdapter(getActivity());
        binding.recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        GridItemDecoration.Builder builder = new GridItemDecoration.Builder(getActivity());
        builder.color(R.color.transparent);
        builder.size(CommonUtil.dip2px(getActivity(), 1));
        binding.recyclerView.addItemDecoration(new GridItemDecoration(builder));
        binding.recyclerView.setAdapter(adapter);
        adapter.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view, Object object) {
                if (recommendData != null) {
                    Intent intent = new Intent(getActivity(), WorkDetailActivity.class);
                    BaseData baseData = new BaseData();
                    baseData.setData(adapter.getList());
                    intent.putExtra("baseData", baseData);
                    intent.putExtra("position", (int) object);
                    getActivity().startActivity(intent);
                }
            }

            @Override
            public void onLongClick(View view, Object object) {

            }
        });


        binding.springMine.setHeader(new SpringViewNewHeader(getContext()));
        binding.springMine.setFooter(new SpringViewNewFooter(getContext()));
        binding.springMine.setType(SpringView.Type.FOLLOW);
        binding.springMine.setListener(new SpringView.OnFreshListener() {
            @Override
            public void onRefresh() {
                isRefresh = true;
                initData(Constants.perPage, 1);
            }

            @Override
            public void onLoadmore() {
                if (recommendData != null && recommendData.getCode() == 200 && recommendData.getData() != null &&
                        recommendData.getData().getCurrent_page() < recommendData.getData().getLast_page()) {
                    isRefresh = false;
                    initData(Constants.perPage, recommendData.getData().getCurrent_page() + 1);
                } else {
                    binding.springMine.onFinishFreshAndLoad();
                    ToastUtils.showShort(getActivity(), "没有更多了");
                }
            }
        });

        return binding.getRoot();
    }

    @Override
    public void onResume() {
        super.onResume();
        if (recommendData != null && recommendData.getCode() == 200 && recommendData.getData() != null) {
            isRefresh = true;
            initData(adapter.getList().size(), 1);
        } else {
            isRefresh = true;
            initData(Constants.perPage, 1);
        }
    }

    private void initData(int perPage, int currentPage) {
        SendRequest.videoAttention(getUid(),perPage, currentPage, new GenericsCallback<RecommendData>(new JsonGenericsSerializator()) {
            @Override
            public void onError(Call call, Exception e, int id) {
                binding.springMine.onFinishFreshAndLoad();
            }

            @Override
            public void onResponse(RecommendData response, int id) {
                binding.springMine.onFinishFreshAndLoad();
                if (response.getCode() == 200 && response.getData() != null && response.getData().getData() != null) {
                    recommendData = response;
                    if (isRefresh) {
                        adapter.refreshData(response.getData().getData());
                    } else {
                        adapter.loadMoreData(response.getData().getData());
                    }
                }
            }

        });
    }
}