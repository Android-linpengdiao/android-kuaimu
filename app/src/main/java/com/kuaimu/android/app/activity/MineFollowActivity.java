package com.kuaimu.android.app.activity;

import androidx.databinding.DataBindingUtil;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import android.view.View;

import com.baselibrary.utils.ToastUtils;
import com.kuaimu.android.app.R;
import com.kuaimu.android.app.adapter.MineFollowAdapter;
import com.kuaimu.android.app.databinding.ActivityMineFollowBinding;
import com.kuaimu.android.app.model.FollowUserData;
import com.kuaimu.android.app.view.OnClickListener;
import com.okhttp.SendRequest;
import com.okhttp.callbacks.GenericsCallback;
import com.okhttp.sample_okhttp.JsonGenericsSerializator;

import okhttp3.Call;

public class MineFollowActivity extends BaseActivity implements View.OnClickListener {

    private ActivityMineFollowBinding binding;
    private MineFollowAdapter adapter;
    private FollowUserData followUserData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_mine_follow);

        binding.back.setOnClickListener(this);

        adapter = new MineFollowAdapter(this);
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        binding.recyclerView.setAdapter(adapter);
        adapter.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view, Object object) {
                FollowUserData.DataBeanX.DataBean dataBean = (FollowUserData.DataBeanX.DataBean) object;
                setFollow(dataBean);
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

    }

    @Override
    public void onResume() {
        super.onResume();
        binding.swipeRefreshLayout.setRefreshing(true);
        initData();
    }

    private void initData() {
        SendRequest.follows(getUid(), new GenericsCallback<FollowUserData>(new JsonGenericsSerializator()) {
            @Override
            public void onError(Call call, Exception e, int id) {
                binding.swipeRefreshLayout.setRefreshing(false);
            }

            @Override
            public void onResponse(FollowUserData response, int id) {
                followUserData = response;
                binding.swipeRefreshLayout.setRefreshing(false);
                if (response.getCode() == 200 && response.getData() != null && response.getData()!= null) {
                    adapter.refreshData(response.getData().getData());
                } else {
                    ToastUtils.showShort(MineFollowActivity.this, response.getMsg());
                }
            }

        });
    }

    private void setFollow(final FollowUserData.DataBeanX.DataBean dataBean) {
//        String url = dataBean.getAttention() != -1 ? APIUrls.url_centerUnFollow : APIUrls.url_centerFollow;
//        SendRequest.centerFollow(getUserInfo().getData().getId(), dataBean.getId(), url, new StringCallback() {
//            @Override
//            public void onError(Call call, Exception e, int id) {
//
//            }
//
//            @Override
//            public void onResponse(String response, int id) {
//                try {
//                    if (!CommonUtil.isBlank(response)) {
//                        JSONObject jsonObject = new JSONObject(response);
//                        if (jsonObject.optInt("code") == 200) {
//                            dataBean.setAttention(dataBean.getAttention() != -1 ? -1 : 0);
//                            if (dataBean.getAttention() != -1) {
//                                ToastUtils.showShort(MineFollowActivity.this, "已关注");
//                            }
//                            adapter.notifyItemChanged(followUserData.getData().getData().indexOf(dataBean));
//                        } else {
//                            ToastUtils.showShort(MineFollowActivity.this, jsonObject.optString("msg"));
//                        }
//                    } else {
//                        ToastUtils.showShort(MineFollowActivity.this, "请求失败");
//                    }
//                } catch (Exception e) {
//                    e.printStackTrace();
//                    ToastUtils.showShort(MineFollowActivity.this, "请求失败");
//                }
//
//            }
//        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back:
                finish();
                break;
        }
    }
}