package com.kuaimu.android.app.activity;

import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.view.View;

import com.baselibrary.manager.DialogManager;
import com.baselibrary.utils.GlideLoader;
import com.baselibrary.utils.ToastUtils;
import com.kuaimu.android.app.R;
import com.kuaimu.android.app.databinding.ActivityProductDetailBinding;
import com.kuaimu.android.app.model.GoodDetail;
import com.kuaimu.android.app.utils.DialogUtil;
import com.okhttp.SendRequest;
import com.okhttp.callbacks.GenericsCallback;
import com.okhttp.callbacks.StringCallback;
import com.okhttp.sample_okhttp.JsonGenericsSerializator;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;

import okhttp3.Call;

public class ProductDetailActivity extends BaseActivity {

    private ActivityProductDetailBinding binding;
    private GoodDetail goodDetail;
    private int workId;
    private int uid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_product_detail);

        binding.back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        binding.deleteGoodView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (goodDetail != null) {
                    DialogManager.showConfirmDialog(ProductDetailActivity.this, "确定要删除商品？", new DialogManager.Listener() {
                        @Override
                        public void onItemLeft() {

                        }

                        @Override
                        public void onItemRight() {
                            deleteGood();
                            finish();
                        }
                    });

                }
            }
        });
        binding.editProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (goodDetail != null) {
                    Bundle bundle = new Bundle();
                    bundle.putInt("type", 1);
                    bundle.putSerializable("goodDetail", goodDetail);
                    openActivity(ReleaseProductActivity.class, bundle);
                }
            }
        });
        binding.cover.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (goodDetail != null) {
                    DialogUtil.getInstance().showMoreImageView(ProductDetailActivity.this, new ArrayList<String>(Arrays.asList(goodDetail.getData().getImg())), 0);
                }
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        initData();
    }

    private void deleteGood() {
        SendRequest.deleteGood(getUid(), goodDetail.getData().getId(), new StringCallback() {


            @Override
            public void onError(Call call, Exception e, int id) {

            }

            @Override
            public void onResponse(String response, int id) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    if (jsonObject.optInt("code") == 200) {
                        ToastUtils.showShort(ProductDetailActivity.this, "删除成功");
                        finish();
                    } else {
                        ToastUtils.showShort(ProductDetailActivity.this, "删除失败 :" + jsonObject.optString("msg"));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    ToastUtils.showShort(ProductDetailActivity.this, "删除失败");
                }
            }
        });
    }

    private void initData() {
        if (getIntent().hasExtra("id")) {
            workId = getIntent().getIntExtra("id", 0);
            uid = getIntent().getIntExtra("uid", 0);
            if (uid == getUid()) {
                binding.deleteGoodView.setVisibility(View.VISIBLE);
                binding.editProduct.setVisibility(View.VISIBLE);
            }
        }
        SendRequest.goodDetail(workId, new GenericsCallback<GoodDetail>(new JsonGenericsSerializator()) {

            @Override
            public void onError(Call call, Exception e, int id) {

            }

            @Override
            public void onResponse(GoodDetail response, int id) {
                if (response.getCode() == 200 && response.getData() != null) {
                    goodDetail = response;
                    initView(response);
                } else {
                    ToastUtils.showShort(ProductDetailActivity.this, response.getMsg());
                }
            }

        });
    }

    private void initView(GoodDetail response) {
        binding.titleView.setText(response.getData().getName());
        binding.descView.setText(response.getData().getDesc());
        GlideLoader.LoderImage(ProductDetailActivity.this, response.getData().getImg(), binding.cover);
    }
}