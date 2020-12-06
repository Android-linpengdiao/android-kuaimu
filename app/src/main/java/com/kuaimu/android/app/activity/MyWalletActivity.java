package com.kuaimu.android.app.activity;

import androidx.databinding.DataBindingUtil;

import okhttp3.Call;

import android.os.Bundle;
import android.view.View;

import com.kuaimu.android.app.R;
import com.kuaimu.android.app.databinding.ActivityMyWalletBinding;
import com.kuaimu.android.app.model.WalletInfoData;
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
        binding.cashPayTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openActivity(CashPayActivity.class);
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        walletInfo();
    }

    private void walletInfo() {
        SendRequest.walletInfo(getUid(true), new GenericsCallback<WalletInfoData>(new JsonGenericsSerializator()) {
            @Override
            public void onError(Call call, Exception e, int id) {

            }

            @Override
            public void onResponse(WalletInfoData response, int id) {
                if (response.getCode() == 200) {
                    binding.walletTokenTextView.setText(response.getData().getWallet_token() + "");
                    binding.incomeTokenTextView.setText(response.getData().getIncome_token() + "");
                }
            }
        });
    }
}