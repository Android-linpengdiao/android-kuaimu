package com.kuaimu.android.app.activity;

import android.content.Intent;

import androidx.databinding.DataBindingUtil;

import android.os.Bundle;

import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.View;

import com.baselibrary.utils.ToastUtils;
import com.kuaimu.android.app.NavData;
import com.kuaimu.android.app.R;
import com.kuaimu.android.app.adapter.VideoTypeAdapter;
import com.kuaimu.android.app.databinding.ActivityVideoTypeBinding;
import com.kuaimu.android.app.view.OnClickListener;
import com.okhttp.SendRequest;
import com.okhttp.callbacks.GenericsCallback;
import com.okhttp.sample_okhttp.JsonGenericsSerializator;

import okhttp3.Call;

public class VideoTypeActivity extends BaseActivity implements View.OnClickListener {

    private ActivityVideoTypeBinding binding;
    private VideoTypeAdapter adapter;
    private NavData.DataBean dataBean;

    private int type = 0; //0 视频分类 ; 1 个性标签 ; 2 行业标签

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_video_type);
        setStatusBarDarkTheme(true);

        binding.back.setOnClickListener(this);
        binding.confirm.setOnClickListener(this);

        if (getIntent().hasExtra("type")) {
            type = getIntent().getIntExtra("type", 0);
            binding.title.setText(type == 0 ? "选择分类" : getIntent().getIntExtra("type", 0) == 1 ? "选择个人标签" : "选择行业标签");
        }

        adapter = new VideoTypeAdapter(this);
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        binding.recyclerView.setAdapter(adapter);
        adapter.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view, Object object) {
                if (object instanceof NavData.DataBean)
                    dataBean = (NavData.DataBean) object;
            }

            @Override
            public void onLongClick(View view, Object object) {

            }
        });

        if (type == 1) {
            SendRequest.personCategory(new GenericsCallback<NavData>(new JsonGenericsSerializator()) {
                @Override
                public void onError(Call call, Exception e, int id) {

                }

                @Override
                public void onResponse(NavData response, int id) {
                    if (response.getCode() == 200 && response.getData() != null) {
                        NavData.DataBean dataBean = new NavData.DataBean();
                        dataBean.setName("无");
                        response.getData().add(0,dataBean);
                        adapter.refreshData(response.getData());
                    } else {
                        ToastUtils.showShort(VideoTypeActivity.this, response.getMsg());
                    }
                }

            });
        } else if (type == 0 || type == 2) {
            SendRequest.category(new GenericsCallback<NavData>(new JsonGenericsSerializator()) {
                @Override
                public void onError(Call call, Exception e, int id) {

                }

                @Override
                public void onResponse(NavData response, int id) {
                    if (response.getCode() == 200 && response.getData() != null) {
                        adapter.refreshData(response.getData());
                    } else {
                        ToastUtils.showShort(VideoTypeActivity.this, response.getMsg());
                    }
                }

            });
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.confirm:
                if (dataBean != null && dataBean.getStatus() == 1) {
                    Intent intent = new Intent();
                    intent.putExtra("nav", dataBean);
                    setResult(RESULT_OK, intent);
                    finish();
                } else {
                    ToastUtils.showShort(VideoTypeActivity.this, "请选择分类");
                }

                break;
        }
    }
}