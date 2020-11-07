package com.kuaimu.android.app.activity;

import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import okhttp3.Call;

import android.os.Bundle;
import android.view.View;

import com.baselibrary.utils.ToastUtils;
import com.kuaimu.android.app.R;
import com.kuaimu.android.app.adapter.MainNoticeAdapter;
import com.kuaimu.android.app.databinding.ActivityChatListBinding;
import com.kuaimu.android.app.model.LeaveUserData;
import com.kuaimu.android.app.view.OnClickListener;
import com.okhttp.SendRequest;
import com.okhttp.callbacks.GenericsCallback;
import com.okhttp.sample_okhttp.JsonGenericsSerializator;

public class ChatListActivity extends BaseActivity {

    private ActivityChatListBinding binding;
    private MainNoticeAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_list);

        binding = DataBindingUtil.setContentView(this,R.layout.activity_chat_list);

        binding.back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        adapter = new MainNoticeAdapter(this);
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        binding.recyclerView.setAdapter(adapter);
        adapter.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view, Object object) {
                if (object instanceof LeaveUserData.DataBean) {
                    LeaveUserData.DataBean dataBean = (LeaveUserData.DataBean) object;
                    if (dataBean.getTourist_id() == getUid()) {
                        if (dataBean.getBe_tourist() != null) {
                            Bundle bundle = new Bundle();
                            bundle.putInt("uid", dataBean.getBe_tourist().getId());
                            openActivity(ChatActivity.class, bundle);
                        }
                    }else {
                        if (dataBean.getTourist() != null) {
                            Bundle bundle = new Bundle();
                            bundle.putInt("uid", dataBean.getTourist().getId());
                            openActivity(ChatActivity.class, bundle);
                        }

                    }
                }
            }

            @Override
            public void onLongClick(View view, Object object) {

            }
        });

        initData();
    }

    @Override
    public void onResume() {
        super.onResume();
        initData();
    }

    private void initData() {
        SendRequest.leaveUser(getUid(), new GenericsCallback<LeaveUserData>(new JsonGenericsSerializator()) {
            @Override
            public void onError(Call call, Exception e, int id) {
            }

            @Override
            public void onResponse(LeaveUserData response, int id) {
                if (response.getCode() == 200 && response.getData() != null) {
                    adapter.refreshData(response.getData());
                } else {
                    ToastUtils.showShort(ChatListActivity.this, response.getMsg());
                }
            }

        });
    }
}
