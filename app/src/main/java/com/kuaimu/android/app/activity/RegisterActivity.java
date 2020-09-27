package com.kuaimu.android.app.activity;

import androidx.databinding.DataBindingUtil;

import android.os.CountDownTimer;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;

import com.baselibrary.UserInfo;
import com.baselibrary.utils.CommonUtil;
import com.baselibrary.utils.ToastUtils;
import com.kuaimu.android.app.MainActivity;
import com.kuaimu.android.app.MyApplication;
import com.kuaimu.android.app.R;
import com.kuaimu.android.app.databinding.ActivityRegisterBinding;
import com.okhttp.SendRequest;
import com.okhttp.callbacks.GenericsCallback;
import com.okhttp.callbacks.StringCallback;
import com.okhttp.sample_okhttp.JsonGenericsSerializator;

import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.Call;

public class RegisterActivity extends BaseActivity {

    private static final String TAG = "RegisterActivity";
    private ActivityRegisterBinding binding;
    private CountDownTimer timer;

    private String third_type = "";
    private String third_value = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_register);
        setStatusBarDarkTheme(false);

        binding.back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        binding.tvSendCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                phoneCode();
            }
        });
        binding.tvConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!CommonUtil.isBlank(third_value)) {
                    bindThird();
                } else {
                    register();
                }

            }
        });

        binding.ivShowPassword.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (binding.ivShowPassword.isSelected()) {
                    binding.ivShowPassword.setSelected(false);
                    //从密码可见模式变为密码不可见模式
                    binding.etPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                } else {
                    binding.ivShowPassword.setSelected(true);
                    //从密码不可见模式变为密码可见模式
                    binding.etPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }
                binding.etPassword.setSelection(binding.etPassword.getText().length());
            }

        });

        binding.checkBox.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                binding.checkBox.setSelected(!binding.checkBox.isSelected());
            }

        });

        binding.etPhone.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
                if (null != charSequence) {
                    binding.tvConfirm.setSelected(charSequence.length() < 11 ? false : true);
                    binding.tvConfirm.setEnabled(charSequence.length() < 11 ? false : true);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        if (getIntent().getExtras() != null) {
            third_type = getIntent().getExtras().getString("third_type");
            third_value = getIntent().getExtras().getString("third_value");
            Log.i(TAG, "onCreate: type = " + third_type);
            Log.i(TAG, "onCreate: type_id = " + third_value);
        }

    }

    private void phoneCode() {
        String phone = binding.etPhone.getText().toString().trim();
        if (phone.length() < 11) {
            ToastUtils.showShort(RegisterActivity.this, "手机号码不正确");
            return;
        }
        String key;
        if (!CommonUtil.isBlank(third_value)) {
            key = "bind.third";
        } else {
            key = "register.code";
        }
        SendRequest.phoneCode(phone, key, new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {

            }

            @Override
            public void onResponse(String response, int id) {
                try {
                    JSONObject json = new JSONObject(response);
                    if (json.optInt("code") == 200) {
                        binding.tvSendCode.setEnabled(false);
                        timer = new CountDownTimer(60000, 1000) {
                            @Override
                            public void onTick(long millisUntilFinished) {
                                binding.tvSendCode.setText(millisUntilFinished / 1000 + "");
                            }

                            @Override
                            public void onFinish() {
                                binding.tvSendCode.setEnabled(true);
                                binding.tvSendCode.setText("获取验证码");
                            }
                        }.start();
                        ToastUtils.showShort(RegisterActivity.this, "验证码发送成功");
                    } else {
                        String msg = json.getString("msg");
                        ToastUtils.showShort(getApplication(), msg);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void register() {
        String phone = binding.etPhone.getText().toString().trim();
        String authCode = binding.etCode.getText().toString().trim();
        String password = binding.etPassword.getText().toString().trim();

        if (phone.length() < 11) {
            ToastUtils.showShort(RegisterActivity.this, "手机号码不正确");
            return;
        }
        if (CommonUtil.isBlank(authCode)) {
            ToastUtils.showShort(RegisterActivity.this, "验证码不能为空");
            return;
        }
        if (password.length() < 6) {
            ToastUtils.showShort(RegisterActivity.this, "密码不能小于6位");
            return;
        }
        if (!binding.checkBox.isSelected()) {
            ToastUtils.showShort(RegisterActivity.this, "请同意用户注册协议");
            return;
        }

        SendRequest.register(phone, password, authCode, third_type, third_value, new GenericsCallback<UserInfo>(new JsonGenericsSerializator()) {
            @Override
            public void onError(Call call, Exception e, int id) {

            }

            @Override
            public void onResponse(UserInfo response, int id) {
                if (response.getCode() == 200) {
                    MyApplication.getInstance().setUserInfo(response);
                    openActivity(MainActivity.class);
                    ToastUtils.showShort(RegisterActivity.this, "注册成功");
                    finish();
                } else {
                    ToastUtils.showShort(getApplication(), response.getMsg());
                }
            }

        });
    }

    private void bindThird() {
        String phone = binding.etPhone.getText().toString().trim();
        String authCode = binding.etCode.getText().toString().trim();
        String password = binding.etPassword.getText().toString().trim();

        if (phone.length() < 11) {
            ToastUtils.showShort(RegisterActivity.this, "手机号码不正确");
            return;
        }
        if (CommonUtil.isBlank(authCode)) {
            ToastUtils.showShort(RegisterActivity.this, "验证码不能为空");
            return;
        }
        if (password.length() < 6) {
            ToastUtils.showShort(RegisterActivity.this, "密码不能小于6位");
            return;
        }
        if (!binding.checkBox.isSelected()) {
            ToastUtils.showShort(RegisterActivity.this, "请同意用户注册协议");
            return;
        }

        SendRequest.bindThird(phone, password, authCode, third_type, third_value, new GenericsCallback<UserInfo>(new JsonGenericsSerializator()) {
            @Override
            public void onError(Call call, Exception e, int id) {

            }

            @Override
            public void onResponse(UserInfo response, int id) {
                if (response.getCode() == 200) {
                    MyApplication.getInstance().setUserInfo(response);
                    openActivity(MainActivity.class);
                    ToastUtils.showShort(RegisterActivity.this, "注册成功");
                    finish();
                } else {
                    ToastUtils.showShort(getApplication(), response.getMsg());
                }
            }

        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (timer != null) {
            timer.cancel();
            timer = null;
        }
    }
}