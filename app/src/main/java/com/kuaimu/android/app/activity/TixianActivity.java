package com.kuaimu.android.app.activity;

import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.view.View;

import com.baselibrary.utils.CommonUtil;
import com.baselibrary.utils.ToastUtils;
import com.kuaimu.android.app.R;
import com.kuaimu.android.app.databinding.ActivityTixianBinding;

public class TixianActivity extends BaseActivity {

    private ActivityTixianBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_tixian);
        binding.back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        binding.confirmTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String money = binding.moneyEditText.getText().toString().trim();
                String wechat = binding.wechatEditText.getText().toString().trim();
                String name = binding.nameEditText.getText().toString().trim();
                String remark = binding.remarkEditText.getText().toString().trim();

                if (CommonUtil.isBlank(money)) {
                    ToastUtils.showShort(TixianActivity.this, "请输入提现金额");
                    return;
                }

                if (CommonUtil.isBlank(wechat)) {
                    ToastUtils.showShort(TixianActivity.this, "请输入您的微信号");
                    return;
                }

                if (CommonUtil.isBlank(name)) {
                    ToastUtils.showShort(TixianActivity.this, "请输入您的真实姓名");
                    return;
                }

                if (CommonUtil.isBlank(remark)) {
                    ToastUtils.showShort(TixianActivity.this, "请输入备注信息");
                    return;
                }
            }
        });
    }
}
