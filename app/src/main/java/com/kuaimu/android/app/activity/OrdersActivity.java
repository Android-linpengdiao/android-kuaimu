package com.kuaimu.android.app.activity;

import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import okhttp3.Call;

import android.os.Bundle;
import android.view.View;

import com.kuaimu.android.app.R;
import com.kuaimu.android.app.adapter.OrdersAdapter;
import com.kuaimu.android.app.databinding.ActivityOrdersBinding;
import com.kuaimu.android.app.model.OrdersData;
import com.okhttp.SendRequest;
import com.okhttp.callbacks.GenericsCallback;
import com.okhttp.sample_okhttp.JsonGenericsSerializator;

public class OrdersActivity extends BaseActivity {

    private ActivityOrdersBinding binding;
    private OrdersAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_orders);
        binding.back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        adapter = new OrdersAdapter(this);
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        binding.recyclerView.setAdapter(adapter);
        SendRequest.walletRecord(getUid(), new GenericsCallback<OrdersData>(new JsonGenericsSerializator()) {
            @Override
            public void onError(Call call, Exception e, int id) {

            }

            @Override
            public void onResponse(OrdersData response, int id) {
                if (response.getCode() == 200) {
                    adapter.refreshData(response.getData().getData());
                }
            }
        });
    }
}