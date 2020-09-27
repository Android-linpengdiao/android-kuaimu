package com.kuaimu.android.app.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import androidx.databinding.DataBindingUtil;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import okhttp3.Call;

import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RadioGroup;

import com.baselibrary.utils.CommonUtil;
import com.baselibrary.utils.ToastUtils;
import com.kuaimu.android.app.R;
import com.kuaimu.android.app.databinding.ActivityOpenedVipBinding;
import com.kuaimu.android.app.manager.PayManager;
import com.kuaimu.android.app.model.UserPriceData;
import com.kuaimu.android.app.utils.Config;
import com.okhttp.SendRequest;
import com.okhttp.callbacks.GenericsCallback;
import com.okhttp.callbacks.StringCallback;
import com.okhttp.sample_okhttp.JsonGenericsSerializator;

import org.json.JSONException;
import org.json.JSONObject;

public class OpenedVIPActivity extends BaseActivity {

    private ActivityOpenedVipBinding binding;
    private int purpose = 1;//1 企业   2 个人
    private String payType = "wechat";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_opened_vip);
        if (getIntent().getExtras() != null) {
            purpose = getIntent().getExtras().getInt("purpose");
        } else {
            finish();
        }

        binding.title.setText(purpose == 1 ? "开通企业会员" : "开通个人会员");
        binding.etName.setHint(purpose == 1 ? "请输入企业名称" : "请输入真实姓名");
        binding.etIDCard.setHint(purpose == 1 ? "请输入企业组织机构代码" : "请输入身份证号码");

        binding.back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        binding.radioGroupView.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.radio_button_wchat:
                        payType = "wechat";
                        break;
                    case R.id.radio_button_alipay:
                        payType = "alipay";
                        break;
                    default:
                        break;
                }
            }
        });
        binding.tvConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = binding.etName.getText().toString().trim();
                String idcard = binding.etIDCard.getText().toString().trim();

                if (CommonUtil.isBlank(name)) {
                    ToastUtils.showShort(OpenedVIPActivity.this, purpose == 1 ? "请输入企业名称" : "请输入真实姓名");
                    return;
                }

                if (CommonUtil.isBlank(idcard)) {
                    ToastUtils.showShort(OpenedVIPActivity.this, purpose == 1 ? "请输入企业组织机构代码" : "请输入身份证号码");
                    return;
                }
                userPrice(name, idcard);
            }
        });

        myRegister();
    }

    private void userPrice(final String name, final String idcard) {
        SendRequest.userPrice(getUid(true), new GenericsCallback<UserPriceData>(new JsonGenericsSerializator()) {
            @Override
            public void onError(Call call, Exception e, int id) {

            }

            @Override
            public void onResponse(UserPriceData response, int id) {
                if (response.getCode() == 200 && response.getData() != null && response.getData().size() > 0) {
                    for (UserPriceData.DataBean dataBean : response.getData()) {
                        if (dataBean.getPurpose() == purpose) {
                            cashPay(dataBean.getPrice(), name, idcard);
                            return;
                        }
                    }
                } else {
                    ToastUtils.showShort(OpenedVIPActivity.this, "服务错误");
                }
            }
        });
    }

    private void cashPay(String money, String name, String idcard) {
        SendRequest.cashPay(getUserInfo().getData().getId(), money, payType, purpose, name, idcard, new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {

            }

            @Override
            public void onResponse(String response, int id) {
                try {
                    JSONObject object = new JSONObject(response);
                    if (object.optInt("code") == 200) {
                        JSONObject data = object.optJSONObject("data");
                        String msg = data.optString("content");
                        if (payType.equals("wechat")) {
                            try {
                                if (!CommonUtil.isBlank(msg)) {
                                    JSONObject dataJson = new JSONObject(msg);
                                    String appId = dataJson.getString("appid");
                                    String partnerId = dataJson.getString("partnerid");
                                    String prepayId = dataJson.getString("prepayid");
                                    String nonceStr = dataJson.getString("noncestr");
                                    String timeStamp = dataJson.getString("timestamp");
                                    String sign = dataJson.getString("sign");
                                    PayManager.WeChatPay(OpenedVIPActivity.this, appId, partnerId, prepayId, nonceStr, timeStamp, sign);
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        } else if (payType.equals("alipay")) {
                            PayManager.aliPay(OpenedVIPActivity.this, msg, new PayManager.PayListener() {
                                @Override
                                public void onSuccess() {
                                    paySuccess();
                                }

                                @Override
                                public void onFail() {
                                    payFail();

                                }

                                @Override
                                public void onCancel() {
                                    ToastUtils.showShort(OpenedVIPActivity.this, "取消支付");
                                }
                            });
                        }
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private static final String TAG = "OpenedVIPActivity";

    /**
     * 支付成功
     */
    private void paySuccess() {
        Log.i(TAG, "paySuccess: ");
//        Bundle bundle = new Bundle();
//        bundle.putBoolean("flag", true);
//        openActivity(PayResultActivity.class, bundle);
        baseInfo();
        finishActivity(MyVIPActivity.class);
        finish();
    }

    //支付失败
    private void payFail() {
        ToastUtils.showShort(OpenedVIPActivity.this, "支付失败");
        Log.i(TAG, "payFail: ");
        Bundle bundle = new Bundle();
        bundle.putBoolean("flag", false);
//        openActivity(PayResultActivity.class, bundle);
//        finish();

    }

    private MyBroadcastReceiver receiver;

    private void myRegister() {
        if (receiver == null) {
            receiver = new MyBroadcastReceiver();
        }
        LocalBroadcastManager.getInstance(getApplicationContext()).registerReceiver(receiver, new IntentFilter(Config.wechat_pay_result));
    }

    private void unRegister() {
        if (receiver != null) {
            LocalBroadcastManager.getInstance(getApplicationContext()).unregisterReceiver(receiver);
            receiver = null;
        }
    }


    class MyBroadcastReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {

            String action = intent.getAction();
            if (Config.wechat_pay_result.equals(action)) {
                boolean result = intent.getBooleanExtra("result", false);
                int errCode = intent.getIntExtra("errCode", -1);
                if (result) {
                    paySuccess();
                } else {
                    if (errCode == -2) {
                        payFail();
                        ToastUtils.showShort(OpenedVIPActivity.this, "取消支付");
                    } else {
                        payFail();
                    }
                }
            }

        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unRegister();
    }
}