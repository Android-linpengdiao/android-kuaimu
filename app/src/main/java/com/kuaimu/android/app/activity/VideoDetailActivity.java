package com.kuaimu.android.app.activity;

import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

import com.kuaimu.android.app.R;
import com.kuaimu.android.app.databinding.ActivityVideoDetailBinding;
import com.kuaimu.android.app.model.HomeDetail;
import com.okhttp.SendRequest;
import com.okhttp.callbacks.GenericsCallback;
import com.okhttp.sample_okhttp.JsonGenericsSerializator;

import okhttp3.Call;

public class VideoDetailActivity extends BaseActivity {
    
    private ActivityVideoDetailBinding binding;
    private int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_video_detail);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        setStatusBarDarkTheme(false);

        homeDetail();
    }

    private void homeDetail() {
        if (getIntent().hasExtra("id")) {
            id = getIntent().getIntExtra("id", 0);
        } else {
            finish();
        }
        SendRequest.homeDetail(getUid(), id, new GenericsCallback<HomeDetail>(new JsonGenericsSerializator()) {
            @Override
            public void onError(Call call, Exception e, int id) {

            }

            @Override
            public void onResponse(HomeDetail response, int id) {
                if (response.getCode() == 200) {
                    binding.tvComment.setText(String.valueOf(response.getData().getComment_num()));
                    binding.tvShare.setText(String.valueOf(response.getData().getShare_num()));
                    binding.ivFollow.setVisibility(response.getData().isIs_liker() ? View.INVISIBLE : View.VISIBLE);
                }
            }
        });
    }
}