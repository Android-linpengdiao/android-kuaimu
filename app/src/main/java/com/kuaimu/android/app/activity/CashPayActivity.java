package com.kuaimu.android.app.activity;

import androidx.databinding.DataBindingUtil;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.GridLayoutManager;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RadioGroup;

import com.baselibrary.utils.CommonUtil;
import com.baselibrary.utils.ToastUtils;
import com.kuaimu.android.app.R;
import com.kuaimu.android.app.adapter.CoinAdapter;
import com.kuaimu.android.app.databinding.ActivityCashPayBinding;
import com.kuaimu.android.app.manager.PayManager;
import com.kuaimu.android.app.model.WalletSetData;
import com.kuaimu.android.app.utils.Config;
import com.kuaimu.android.app.view.OnClickListener;
import com.okhttp.SendRequest;
import com.okhttp.callbacks.GenericsCallback;
import com.okhttp.callbacks.StringCallback;
import com.okhttp.sample_okhttp.JsonGenericsSerializator;

import org.json.JSONException;
import org.json.JSONObject;


import okhttp3.Call;

public class CashPayActivity extends BaseActivity {

    private ActivityCashPayBinding binding;
    private CoinAdapter adapter;
    private WalletSetData.DataBean dataBean;
    private String payType = "wechat";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_cash_pay);

        binding.back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        adapter = new CoinAdapter(this);
        binding.recyclerView.setLayoutManager(new GridLayoutManager(this, 3));
        binding.recyclerView.setAdapter(adapter);
        adapter.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view, Object object) {
                if (object instanceof WalletSetData.DataBean) {
                    dataBean = (WalletSetData.DataBean) object;
                }
            }

            @Override
            public void onLongClick(View view, Object object) {

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

                if (dataBean != null && dataBean.getSelected() == 1) {
                    cashPay();
                } else {
                    ToastUtils.showShort(CashPayActivity.this, "请选择充值金额");
                }
            }
        });

        myRegister();

        initData();
    }

    private void initData() {
        SendRequest.walletSet(getUid(),new GenericsCallback<WalletSetData>(new JsonGenericsSerializator()) {
            @Override
            public void onError(Call call, Exception e, int id) {
            }

            @Override
            public void onResponse(WalletSetData response, int id) {
                if (response.getCode() == 200 && response.getData() != null) {
                    adapter.refreshData(response.getData());
                } else {
                    ToastUtils.showShort(CashPayActivity.this, response.getMsg());
                }
            }

        });
    }

    private void cashPay() {
        SendRequest.cashPay(getUid(), dataBean.getMoney(),  payType, dataBean.getWallet_token(), new StringCallback() {
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
                                            PayManager.WeChatPay(CashPayActivity.this, appId, partnerId, prepayId, nonceStr, timeStamp, sign);
                                        }
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                } else if (payType.equals("alipay")) {
                                    PayManager.aliPay(CashPayActivity.this, msg, new PayManager.PayListener() {
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
                                            ToastUtils.showShort(CashPayActivity.this, "取消支付");
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
        finishActivity(MyWalletActivity.class);
        finish();
    }

    //支付失败
    private void payFail() {
        ToastUtils.showShort(CashPayActivity.this, "支付失败");
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
                        ToastUtils.showShort(CashPayActivity.this, "取消支付");
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