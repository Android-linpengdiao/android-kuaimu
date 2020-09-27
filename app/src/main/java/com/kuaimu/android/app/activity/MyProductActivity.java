package com.kuaimu.android.app.activity;

import androidx.databinding.DataBindingUtil;

import android.os.Bundle;

import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.recyclerview.widget.GridLayoutManager;

import android.view.View;

import com.baselibrary.utils.CommonUtil;
import com.kuaimu.android.app.R;
import com.kuaimu.android.app.adapter.MyProductAdapter;
import com.kuaimu.android.app.databinding.ActivityMyProductBinding;
import com.kuaimu.android.app.model.PersonGood;
import com.kuaimu.android.app.view.GridItemDecoration;
import com.okhttp.SendRequest;
import com.okhttp.callbacks.GenericsCallback;
import com.okhttp.sample_okhttp.JsonGenericsSerializator;

import okhttp3.Call;

public class MyProductActivity extends BaseActivity implements View.OnClickListener {

    private ActivityMyProductBinding binding;
    private MyProductAdapter adapter;
    private int uid = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_my_product);

        if (getIntent().getExtras() != null) {
            uid = getIntent().getExtras().getInt("uid");
            if (uid == getUid()) {
                binding.addProductView.setVisibility(View.VISIBLE);
            }
        }

        binding.back.setOnClickListener(this);
        binding.addProductView.setOnClickListener(this);

        adapter = new MyProductAdapter(this);
        binding.recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        GridItemDecoration.Builder builder = new GridItemDecoration.Builder(this);
        builder.color(R.color.transparent);
        builder.size(CommonUtil.dip2px(this, 10));
        binding.recyclerView.addItemDecoration(new GridItemDecoration(builder));
        binding.recyclerView.setAdapter(adapter);

        binding.swipeRefreshLayout.setColorSchemeColors(getResources().getColor(R.color.colorPrimary));
        binding.swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                initData();
            }
        });
        binding.swipeRefreshLayout.setRefreshing(true);

    }

    @Override
    protected void onResume() {
        super.onResume();
        initData();
    }

    private void initData() {
        SendRequest.personGood(uid, new GenericsCallback<PersonGood>(new JsonGenericsSerializator()) {
            @Override
            public void onError(Call call, Exception e, int id) {
                binding.swipeRefreshLayout.setRefreshing(false);
            }

            @Override
            public void onResponse(PersonGood response, int id) {
                binding.swipeRefreshLayout.setRefreshing(false);
                if (response.getCode() == 200 && response.getData() != null && response.getData() != null) {
                    adapter.refreshData(response.getData());
                }
            }

        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.addProductView:
                Bundle bundle = new Bundle();
                bundle.putInt("type", 0);
                openActivity(ReleaseProductActivity.class, bundle);
                break;
            case R.id.back:
                finish();
                break;
        }
    }
}
