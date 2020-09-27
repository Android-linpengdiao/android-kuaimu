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
import com.kuaimu.android.app.databinding.ActivityTabTypeBinding;
import com.kuaimu.android.app.view.OnClickListener;
import com.okhttp.SendRequest;
import com.okhttp.callbacks.GenericsCallback;
import com.okhttp.sample_okhttp.JsonGenericsSerializator;

import okhttp3.Call;

public class TabTypeActivity extends BaseActivity {

    private ActivityTabTypeBinding binding;
    private VideoTypeAdapter adapter;
    private NavData navData;
    private NavData.DataBean dataBean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_tab_type);
        setStatusBarDarkTheme(true);

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

        SendRequest.category(new GenericsCallback<NavData>(new JsonGenericsSerializator()) {
            @Override
            public void onError(Call call, Exception e, int id) {

            }

            @Override
            public void onResponse(NavData response, int id) {
                if (response.getCode() == 200 && response.getData() != null) {
                    navData = response;
                    adapter.refreshData(response.getData());
                } else {
                    ToastUtils.showShort(TabTypeActivity.this, response.getMsg());
                }
            }

        });

        binding.back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        binding.confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (dataBean != null && dataBean.getStatus() == 1) {
                    for (int i = 0; i < navData.getData().size(); i++) {
                        if (navData.getData().get(i).getId() == dataBean.getId()){
                            Intent intent = new Intent();
                            intent.putExtra("position", i);
                            setResult(RESULT_OK, intent);
                            finish();
                            break;
                        }
                    }
                } else {
                    ToastUtils.showShort(TabTypeActivity.this, "请选择分类");
                }
            }
        });
    }
}
