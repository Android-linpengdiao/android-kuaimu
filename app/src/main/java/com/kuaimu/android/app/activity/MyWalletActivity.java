package com.kuaimu.android.app.activity;

import androidx.databinding.DataBindingUtil;

import okhttp3.Call;

import android.os.Bundle;
import android.view.View;

import com.kuaimu.android.app.R;
import com.kuaimu.android.app.databinding.ActivityMyWalletBinding;
import com.kuaimu.android.app.model.UserPriceData;
import com.kuaimu.android.app.model.VipDetailData;
import com.okhttp.SendRequest;
import com.okhttp.callbacks.GenericsCallback;
import com.okhttp.sample_okhttp.JsonGenericsSerializator;

public class MyWalletActivity extends BaseActivity {
    private ActivityMyWalletBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_my_wallet);
        addActivity(MyWalletActivity.this);

        binding.back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        binding.ordersTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openActivity(OrdersActivity.class);
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        initData();
    }

    private void initData() {
        /**
         * is_vip 1是vip 2不是 vip_type1 企业 2个人 vip_time vip到期时间
         */
        if (getUserInfo().getData().getIs_vip() == 1) {
            vipDetail();
        } else {
            userPrice();

        }
    }

    private void vipDetail() {
        SendRequest.vipDetail(getUid(true), new GenericsCallback<VipDetailData>(new JsonGenericsSerializator()) {
            @Override
            public void onError(Call call, Exception e, int id) {

            }

            @Override
            public void onResponse(VipDetailData response, int id) {
                if (response.getCode() == 200) {
                }
            }
        });
    }

    private void userPrice() {
        SendRequest.userPrice(getUid(true), new GenericsCallback<UserPriceData>(new JsonGenericsSerializator()) {
            @Override
            public void onError(Call call, Exception e, int id) {

            }

            @Override
            public void onResponse(UserPriceData response, int id) {
                if (response.getCode() == 200) {

                }
            }
        });
    }
}