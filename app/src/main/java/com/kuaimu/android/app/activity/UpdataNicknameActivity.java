package com.kuaimu.android.app.activity;

import android.content.Intent;

import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.view.View;

import com.baselibrary.UserInfo;
import com.baselibrary.utils.CommonUtil;
import com.baselibrary.utils.ToastUtils;
import com.kuaimu.android.app.R;
import com.kuaimu.android.app.databinding.ActivityUpdataNicknameBinding;
import com.okhttp.SendRequest;
import com.okhttp.callbacks.GenericsCallback;
import com.okhttp.callbacks.StringCallback;
import com.okhttp.sample_okhttp.JsonGenericsSerializator;

import org.json.JSONObject;

import okhttp3.Call;

public class UpdataNicknameActivity extends BaseActivity implements View.OnClickListener {

    private ActivityUpdataNicknameBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_updata_nickname);

        binding.back.setOnClickListener(this);
        binding.tvConfirm.setOnClickListener(this);

        binding.edContent.setText(getUserInfo().getData().getName());
        binding.edContent.setSelection(getUserInfo().getData().getName().length());

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.tv_confirm:
                final String content = binding.edContent.getText().toString().trim();
                if (CommonUtil.isBlank(content)) {
                    ToastUtils.showShort(UpdataNicknameActivity.this, "请输入昵称,");
                    return;
                }
                editPersonal("name", content);
                break;
        }
    }

    private void editPersonal(final String key, final String value) {
        SendRequest.editBase(getUid(), key, value, new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {

            }

            @Override
            public void onResponse(String response, int id) {
                try {
                    if (!CommonUtil.isBlank(response)) {
                        JSONObject jsonObject = new JSONObject(response);
                        ToastUtils.showShort(UpdataNicknameActivity.this, jsonObject.optString("msg"));
                        if (jsonObject.optInt("code") == 200) {
                            getBaseInfo();
                        }
                    } else {
                        ToastUtils.showShort(UpdataNicknameActivity.this, "编辑失败");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    ToastUtils.showShort(UpdataNicknameActivity.this, "编辑失败");
                }

            }
        });
    }

    private void getBaseInfo() {
        SendRequest.baseInfo(getUserInfo().getData().getId(), new GenericsCallback<UserInfo>(new JsonGenericsSerializator()) {
            @Override
            public void onError(Call call, Exception e, int id) {
            }

            @Override
            public void onResponse(UserInfo response, int id) {
                if (response.getCode() == 200 && response.getData() != null) {
                    setUserInfo(response);
                    Intent intent = new Intent();
                    setResult(RESULT_OK, intent);
                    finish();
                }
            }

        });
    }


}