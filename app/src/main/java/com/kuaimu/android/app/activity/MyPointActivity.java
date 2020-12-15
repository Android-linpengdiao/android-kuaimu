package com.kuaimu.android.app.activity;

import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import okhttp3.Call;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import com.baselibrary.utils.GlideLoader;
import com.kuaimu.android.app.R;
import com.kuaimu.android.app.adapter.OrdersAdapter;
import com.kuaimu.android.app.adapter.PointAdapter;
import com.kuaimu.android.app.databinding.ActivityMyPointBinding;
import com.kuaimu.android.app.model.IntegralData;
import com.kuaimu.android.app.model.LevelData;
import com.kuaimu.android.app.model.OrdersData;
import com.okhttp.SendRequest;
import com.okhttp.callbacks.GenericsCallback;
import com.okhttp.sample_okhttp.JsonGenericsSerializator;
import com.warkiz.widget.IndicatorSeekBar;
import com.warkiz.widget.IndicatorSeekBarType;
import com.warkiz.widget.IndicatorType;
import com.warkiz.widget.TickType;

public class MyPointActivity extends BaseActivity {
    private static final String TAG = "MyPointActivity";
    private ActivityMyPointBinding binding;
    private PointAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_my_point);
        setStatusBarHeight(binding.getRoot());

        binding.back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        adapter = new PointAdapter(this);
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        binding.recyclerView.setAdapter(adapter);


        SendRequest.personIntegral(getUid(), 10, 1, new GenericsCallback<IntegralData>(new JsonGenericsSerializator()) {
            @Override
            public void onError(Call call, Exception e, int id) {

            }

            @Override
            public void onResponse(IntegralData integralData, int id) {
                if (integralData.getCode() == 200 && integralData.getData() != null
                        && integralData.getData().getData() != null && integralData.getData().getData().getData() != null) {

                    adapter.refreshData(integralData.getData().getData().getData());

                    SendRequest.levelSet(new GenericsCallback<LevelData>(new JsonGenericsSerializator()) {
                        @Override
                        public void onError(Call call, Exception e, int id) {

                        }

                        @Override
                        public void onResponse(LevelData response, int id) {
                            if (response.getCode() == 200 && response.getData().size() > 0) {
                                for (int i = 0; i < response.getData().size(); i++) {
                                    if (Integer.parseInt(integralData.getData().getTotal()) < response.getData().get(i).getWallet_token()) {
                                        if (i == 0) {
                                            GlideLoader.LoderDrawable(getApplication(), R.mipmap.icon_qingtong, binding.levelCoverImageView, 0);
                                        } else if (i == 1) {
                                            GlideLoader.LoderDrawable(getApplication(), R.mipmap.icon_baiyin, binding.levelCoverImageView, 0);
                                        } else if (i == 2) {
                                            GlideLoader.LoderDrawable(getApplication(), R.mipmap.icon_huangjin, binding.levelCoverImageView, 0);
                                        } else if (i == 3) {
                                            GlideLoader.LoderDrawable(getApplication(), R.mipmap.icon_bojin, binding.levelCoverImageView, 0);
                                        } else if (i == 4) {
                                            GlideLoader.LoderDrawable(getApplication(), R.mipmap.icon_zuanshi, binding.levelCoverImageView, 0);
                                        } else if (i == 5) {
                                            GlideLoader.LoderDrawable(getApplication(), R.mipmap.icon_wangzhe, binding.levelCoverImageView, 0);
                                        }
                                        binding.levelTitleTextView.setText("LV." + response.getData().get(i).getId() + " " + response.getData().get(i).getTitle());
                                        binding.levelMinTextView.setText("lv." + (response.getData().get(i).getId() - 1));
                                        binding.levelMaxTextView.setText("lv." + response.getData().get(i).getId());
                                        binding.levelMinWalletTokenTextView.setText(integralData.getData().getTotal());
                                        binding.levelMaxWalletTokenTextView.setText(String.valueOf(response.getData().get(i).getWallet_token()));

                                        LinearLayout.LayoutParams levelMinLayoutParams = (LinearLayout.LayoutParams) binding.levelMinWalletTokenTextView.getLayoutParams();
                                        LinearLayout.LayoutParams levelMAxLayoutParams = (LinearLayout.LayoutParams) binding.levelMaxWalletTokenTextView.getLayoutParams();

                                        int total = response.getData().get(i).getWallet_token() - (i > 0 ? response.getData().get(i - 1).getWallet_token() : 0);
                                        int WalletToken = Integer.parseInt(integralData.getData().getTotal()) - (i > 0 ? response.getData().get(i - 1).getWallet_token() : 0);

                                        binding.progress.setMax(total);
                                        binding.progress.setProgress(WalletToken);
                                        binding.progress.setEnabled(false);

//                                        IndicatorSeekBar indicatorSeekBar = new IndicatorSeekBar.Builder(getApplication())
//                                                .setMax(total)
//                                                .setProgress(WalletToken)
//                                                .setSeekBarType(IndicatorSeekBarType.CONTINUOUS)
//                                                .setTickType(TickType.NONE)
//                                                .setTickColor(Color.parseColor("#ffffff"))
//                                                .setTickSize(5)//dp size
//                                                .setTickNum(5)
//                                                .setThumbColor(Color.parseColor("#00000000"))
//                                                .setBackgroundTrackSize(5)//dp size
//                                                .setBackgroundTrackColor(Color.parseColor("#3107C4"))
//                                                .setProgressTrackSize(5)//dp size
//                                                .setProgressTrackColor(Color.parseColor("#F8F6FF"))
//                                                .showIndicator(false)
//                                                .setIndicatorType(IndicatorType.SQUARE_CORNERS)
//                                                .setIndicatorColor(Color.parseColor("#F8F6FF"))
//                                                .build();
//
//                                        binding.progressLayout.addView(indicatorSeekBar);


//                                        levelMinLayoutParams.weight = WalletToken;
//                                        levelMAxLayoutParams.weight = total - WalletToken;

                                        break;
                                    }
                                }
                            }
                        }
                    });

                }
            }
        });
    }
}
