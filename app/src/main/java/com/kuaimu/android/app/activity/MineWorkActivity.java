package com.kuaimu.android.app.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.GridLayoutManager;
import okhttp3.Call;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.baselibrary.MessageBus;
import com.baselibrary.utils.CommonUtil;
import com.baselibrary.utils.ToastUtils;
import com.kuaimu.android.app.R;
import com.kuaimu.android.app.adapter.MineWorkAdapter;
import com.kuaimu.android.app.adapter.WorkAdapter;
import com.kuaimu.android.app.databinding.ActivityMineWorkBinding;
import com.kuaimu.android.app.model.BaseData;
import com.kuaimu.android.app.model.MineWorkData;
import com.kuaimu.android.app.view.GridItemDecoration;
import com.kuaimu.android.app.view.OnClickListener;
import com.okhttp.SendRequest;
import com.okhttp.callbacks.GenericsCallback;
import com.okhttp.sample_okhttp.JsonGenericsSerializator;

import org.greenrobot.eventbus.EventBus;

public class MineWorkActivity extends BaseActivity {
    private ActivityMineWorkBinding binding;

    private WorkAdapter adapter;
    private MineWorkData mineWorkData;
    private int uid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_mine_work);

        binding.back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        adapter = new WorkAdapter(this);
        binding.recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        GridItemDecoration.Builder builder = new GridItemDecoration.Builder(this);
        builder.color(R.color.transparent);
        builder.size(CommonUtil.dip2px(this, 1));
        binding.recyclerView.addItemDecoration(new GridItemDecoration(builder));
        binding.recyclerView.setNestedScrollingEnabled(false);
        binding.recyclerView.setAdapter(adapter);
        adapter.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view, Object object) {
                if (mineWorkData!=null) {
                    Intent intent = new Intent(MineWorkActivity.this, WorkDetailActivity.class);
                    BaseData baseData = new BaseData();
                    baseData.setData(mineWorkData.getData().getData());
                    intent.putExtra("baseData",baseData );
                    intent.putExtra("position",(int)object );
                    startActivity(intent);
                }
            }

            @Override
            public void onLongClick(View view, Object object) {
                MessageBus.Builder builder = new MessageBus.Builder();
                MessageBus messageBus = builder
                        .codeType(MessageBus.msgId_workSelection)
                        .message(0)
                        .build();
                EventBus.getDefault().post(messageBus);
            }
        });

        initData();
    }

    public void initData() {
        SendRequest.centerSelfWork(getUid(), new GenericsCallback<MineWorkData>(new JsonGenericsSerializator()) {
            @Override
            public void onError(Call call, Exception e, int id) {
            }

            @Override
            public void onResponse(MineWorkData response, int id) {
                if (response.getCode() == 200 && response.getData() != null && response.getData().getData() != null) {
                    mineWorkData = response;
                    adapter.refreshData(response.getData().getData());
                } else {
                    ToastUtils.showShort(MineWorkActivity.this, response.getMsg());
                }
            }

        });
    }

}