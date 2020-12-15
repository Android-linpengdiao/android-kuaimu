package com.kuaimu.android.app.activity;

import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.view.View;

import com.baselibrary.utils.CommonUtil;
import com.baselibrary.utils.ToastUtils;
import com.kuaimu.android.app.R;
import com.kuaimu.android.app.databinding.ActivityTixianBinding;
import com.kuaimu.android.app.model.BaseData;
import com.okhttp.SendRequest;
import com.okhttp.callbacks.GenericsCallback;
import com.okhttp.sample_okhttp.JsonGenericsSerializator;

import okhttp3.Call;

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

                SendRequest.withdrawal(getUid(), money, wechat, name, remark, new GenericsCallback<BaseData>(new JsonGenericsSerializator()) {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(BaseData response, int id) {
                        if (response.getCode() == 200) {
                            ToastUtils.showShort(getApplication(), "提交成功");
                            finish();
                        } else {
                            ToastUtils.showShort(getApplication(), response.getMsg());
                        }
                    }
                });
            }
        });
    }
}
