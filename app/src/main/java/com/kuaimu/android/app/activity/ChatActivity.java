package com.kuaimu.android.app.activity;

import androidx.databinding.DataBindingUtil;

import android.os.Bundle;

import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.View;

import com.baselibrary.utils.CommonUtil;
import com.baselibrary.utils.ToastUtils;
import com.kuaimu.android.app.R;
import com.kuaimu.android.app.adapter.ChatMessageAdapter;
import com.kuaimu.android.app.databinding.ActivityChatBinding;
import com.kuaimu.android.app.manager.RecycleViewManager;
import com.kuaimu.android.app.model.HomeLeaveData;
import com.kuaimu.android.app.model.PublishLeaveData;
import com.okhttp.SendRequest;
import com.okhttp.callbacks.GenericsCallback;
import com.okhttp.sample_okhttp.JsonGenericsSerializator;

import java.util.ArrayList;
import java.util.Arrays;

import okhttp3.Call;

public class ChatActivity extends BaseActivity {
    private ActivityChatBinding binding;
    private ChatMessageAdapter adapter;
    private HomeLeaveData homeLeaveData;
    private String name;
    private int uid = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_chat);

        if (getIntent().getExtras() != null) {
//            name = getIntent().getExtras().getString("name");
            uid = getIntent().getExtras().getInt("uid");
        }

        adapter = new ChatMessageAdapter(this);
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        binding.recyclerView.setAdapter(adapter);
        binding.tvMessageSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String content = binding.etMessage.getText().toString();
                if (!CommonUtil.isBlank(content)) {
                    publishLeave(content);
                } else {
                    ToastUtils.showShort(ChatActivity.this, "输入消息");
                }
            }
        });
        binding.back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        initData();
    }

    private void initData() {
        SendRequest.leaveUserDetail(getUid(), uid, new GenericsCallback<HomeLeaveData>(new JsonGenericsSerializator()) {
            @Override
            public void onError(Call call, Exception e, int id) {

            }

            @Override
            public void onResponse(HomeLeaveData response, int id) {
                if (response.getCode() == 200) {
                    homeLeaveData = response;
                    adapter.refreshData(homeLeaveData.getData());
                    if (homeLeaveData.getData() != null && homeLeaveData.getData().size() > 0) {
                        RecycleViewManager.smoothMoveToPosition(binding.recyclerView, adapter.getList().size() - 1);
                    }
                }
            }
        });
    }

    private void publishLeave(String content) {
        SendRequest.publishLeave(getUid(), uid, content, new GenericsCallback<PublishLeaveData>(new JsonGenericsSerializator()) {
            @Override
            public void onError(Call call, Exception e, int id) {

            }

            @Override
            public void onResponse(PublishLeaveData response, int id) {
                if (response.getCode() == 200) {
                    binding.etMessage.setText("");
                    HomeLeaveData.DataBean dataBean = new HomeLeaveData.DataBean();
                    dataBean.setTourist_id(response.getData().getTourist_id());
                    dataBean.setBe_tourist_id(response.getData().getBe_tourist_id());
                    dataBean.setContent(response.getData().getContent());
                    adapter.loadMoreData(new ArrayList<>(Arrays.asList(dataBean)));
                    RecycleViewManager.smoothMoveToPosition(binding.recyclerView, adapter.getList().size() - 1);
                } else {
                    ToastUtils.showShort(ChatActivity.this, response.getMsg());
                }
            }
        });
    }
}