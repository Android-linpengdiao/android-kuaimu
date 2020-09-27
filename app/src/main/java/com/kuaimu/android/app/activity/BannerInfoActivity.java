package com.kuaimu.android.app.activity;

import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.view.View;

import com.kuaimu.android.app.R;
import com.kuaimu.android.app.databinding.ActivityBannerInfoBinding;
import com.kuaimu.android.app.model.BannerData;

public class BannerInfoActivity extends BaseActivity {

    private ActivityBannerInfoBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_banner_info);
        addActivity(this);
        if (getIntent().getExtras() != null) {

            if (getIntent().getExtras().containsKey("dataBean")) {
                BannerData.DataBean dataBean = (BannerData.DataBean) getIntent().getExtras().getSerializable("dataBean");
                binding.title.setText(dataBean.getTitle());
                String address = dataBean.getDesc();
                binding.webView.loadData(address, "text/html; charset=UTF-8", "UTF-8");
//                binding.webView.evaluateJavascript("var meta = document.createElement('meta'); meta.setAttribute('name', 'viewport'); meta.setAttribute('content', 'width=device-width'); document.getElementsByTagName('head')[0].appendChild(meta)", new ValueCallback<String>() {
//                    @Override
//                    public void onReceiveValue(String value) {
//
//                    }
//                });
//                binding.webView.evaluateJavascript("var objs = document.getElementsByTagName('img');for(var i=0;;i++){var img = objs[i];img.style.maxWidth = '100%';img.style.height='auto';}", new ValueCallback<String>() {
//                    @Override
//                    public void onReceiveValue(String value) {
//
//                    }
//                });
            }


        }
        binding.back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}