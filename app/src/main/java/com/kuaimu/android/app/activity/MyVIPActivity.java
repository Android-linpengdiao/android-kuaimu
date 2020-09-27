package com.kuaimu.android.app.activity;

import android.content.Intent;

import androidx.databinding.DataBindingUtil;

import okhttp3.Call;

import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;

import com.baselibrary.utils.CommonUtil;
import com.kuaimu.android.app.R;
import com.kuaimu.android.app.databinding.ActivityMyVipBinding;
import com.kuaimu.android.app.model.UserPriceData;
import com.kuaimu.android.app.model.VipDetailData;
import com.okhttp.SendRequest;
import com.okhttp.callbacks.GenericsCallback;
import com.okhttp.sample_okhttp.JsonGenericsSerializator;

public class MyVIPActivity extends BaseActivity {
    private ActivityMyVipBinding binding;

    private int purpose = 2;//1 企业  2 个人

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_my_vip);
        addActivity(MyVIPActivity.this);

        binding.userVIP.setSelected(true);
        binding.userVIP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                purpose = 2;
                binding.userVIP.setSelected(true);
                binding.enterpriseVIP.setSelected(false);
            }
        });
        binding.enterpriseVIP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                purpose = 1;
                binding.enterpriseVIP.setSelected(true);
                binding.userVIP.setSelected(false);
            }
        });
        binding.confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MyVIPActivity.this, OpenedVIPActivity.class);
                Bundle bundle = new Bundle();
                bundle.putInt("purpose", purpose);
                intent.putExtras(bundle);
                startActivityForResult(intent, 100);
            }
        });
        binding.back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
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
                    binding.vipInfoView.setVisibility(View.VISIBLE);
                    binding.vipRechargeView.setVisibility(View.GONE);
                    binding.title.setText(getUserInfo().getData().getVip_type() == 1 ? "已开通企业会员" : "已开通个人会员");
                    binding.info.setText(response.getData().getName() + "\n" + response.getData().getTrans_no());
                    binding.time.setText("有效期至" + CommonUtil.getVipTime(CommonUtil.getStringToDate(getUserInfo().getData().getVip_time())));
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
                    binding.vipInfoView.setVisibility(View.GONE);
                    binding.vipRechargeView.setVisibility(View.VISIBLE);
                    if (response.getData().size() > 0) {
                        for (UserPriceData.DataBean dataBean : response.getData()) {
                            //1/2 企业/个人
                            if (dataBean.getPurpose() == 1) {
                                binding.enterpriseVIPPriceView.setText(dataBean.getPrice());
                                if (!CommonUtil.isBlank(dataBean.getOrigin_price()) && Double.parseDouble(dataBean.getOrigin_price()) > 0) {
                                    binding.enterpriseOriginPriceView.setVisibility(View.VISIBLE);
                                    binding.enterpriseOriginPriceView.setText("￥"+dataBean.getOrigin_price());
                                    binding.enterpriseOriginPriceView.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG | Paint.ANTI_ALIAS_FLAG); // 设置中划线并加清晰
                                }

                            } else if (dataBean.getPurpose() == 2) {
                                binding.userVIPPriceView.setText(dataBean.getPrice());
                                if (!CommonUtil.isBlank(dataBean.getOrigin_price()) && Double.parseDouble(dataBean.getOrigin_price()) > 0) {
                                    binding.userOriginPriceView.setVisibility(View.VISIBLE);
                                    binding.userOriginPriceView.setText("￥"+dataBean.getOrigin_price());
                                    binding.userOriginPriceView.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG | Paint.ANTI_ALIAS_FLAG); // 设置中划线并加清晰
                                }

                            }
                        }
                    }
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case 100:
                    if (data != null) {
                        int type = data.getIntExtra("purpose", 1);
                        binding.vipRechargeView.setVisibility(View.GONE);
                        binding.vipInfoView.setVisibility(View.VISIBLE);
                        binding.title.setText(type == 1 ? "已开通企业会员" : "已开通个人会员");
                        binding.info.setText(type == 1 ? "北京XXXX有限公司\n901110****6YJ39" : "赵子龙\n232126****0987");
                    }
                    break;
            }
        }
    }
}