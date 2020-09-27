package com.kuaimu.android.app.activity;

import androidx.databinding.DataBindingUtil;
import android.os.Bundle;
import android.text.Html;
import android.view.View;

import com.baselibrary.utils.ToastUtils;
import com.kuaimu.android.app.R;
import com.kuaimu.android.app.databinding.ActivityAboutBinding;
import com.okhttp.SendRequest;
import com.okhttp.callbacks.StringCallback;

import org.json.JSONObject;

import okhttp3.Call;

public class AboutActivity extends BaseActivity implements View.OnClickListener {
    private ActivityAboutBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_about);
        binding.back.setOnClickListener(this);

        initData();

    }

    private void initData() {
        SendRequest.personAbout(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {

            }

            @Override
            public void onResponse(String response, int id) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    if (jsonObject.optInt("code") == 200) {
                        JSONObject object = jsonObject.optJSONObject("data");
                        binding.content.setText(Html.fromHtml(object.optString("desc")));
                    } else {
                        ToastUtils.showShort(AboutActivity.this, jsonObject.optString("msg"));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back:
                finish();
                break;
        }
    }
}
