package com.kuaimu.android.app.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;

import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.os.CountDownTimer;

import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.baselibrary.UserInfo;
import com.baselibrary.utils.CommonUtil;
import com.baselibrary.utils.ToastUtils;
import com.kuaimu.android.app.MainActivity;
import com.kuaimu.android.app.MyApplication;
import com.kuaimu.android.app.R;
import com.kuaimu.android.app.databinding.ActivityLoginBinding;
import com.kuaimu.android.app.manager.TencentHelper;
import com.kuaimu.android.app.manager.WXManager;
import com.kuaimu.android.app.utils.Config;
import com.kuaimu.android.app.weibo.Constants;
import com.okhttp.SendRequest;
import com.okhttp.callbacks.GenericsCallback;
import com.okhttp.callbacks.StringCallback;
import com.okhttp.sample_okhttp.JsonGenericsSerializator;
import com.sina.weibo.sdk.WbSdk;
import com.sina.weibo.sdk.auth.AuthInfo;
import com.sina.weibo.sdk.auth.Oauth2AccessToken;
import com.sina.weibo.sdk.auth.WbAuthListener;
import com.sina.weibo.sdk.auth.WbConnectErrorMessage;
import com.sina.weibo.sdk.auth.sso.SsoHandler;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.Tencent;
import com.tencent.tauth.UiError;

import org.json.JSONObject;

import okhttp3.Call;


public class LoginActivity extends BaseActivity implements View.OnClickListener {
    private static final String TAG = "LoginActivity";
    private ActivityLoginBinding loginBinding;
    private WechatReceiver wxReceiver = null;
    private CountDownTimer timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loginBinding = DataBindingUtil.setContentView(this, R.layout.activity_login);
        setStatusBarDarkTheme(true);

        loginBinding.login.setOnClickListener(this);
        loginBinding.loginWx.setOnClickListener(this);
        loginBinding.loginQq.setOnClickListener(this);
        loginBinding.loginWb.setOnClickListener(this);
        loginBinding.register.setOnClickListener(this);
        loginBinding.forgotPassword.setOnClickListener(this);

        loginBinding.ivShowPassword.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (loginBinding.ivShowPassword.isSelected()) {
                    loginBinding.ivShowPassword.setSelected(false);
                    //从密码可见模式变为密码不可见模式
                    loginBinding.password.setTransformationMethod(PasswordTransformationMethod.getInstance());
                } else {
                    loginBinding.ivShowPassword.setSelected(true);
                    //从密码不可见模式变为密码可见模式
                    loginBinding.password.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }
                loginBinding.password.setSelection(loginBinding.password.getText().length());
            }

        });

        wxReceiver = new WechatReceiver();
        LocalBroadcastManager.getInstance(getApplicationContext()).registerReceiver(wxReceiver, new IntentFilter(Config.wechat_get_token_success));

    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.login:
                passwordLogin();
                break;
            case R.id.register:
                openActivity(RegisterActivity.class);
                break;
            case R.id.forgot_password:
                openActivity(ForgotPasswordActivity.class);
                break;
            case R.id.login_wx:
                WXManager.startAuth(getApplicationContext());
                break;
            case R.id.login_qq:
                TencentHelper.auth(this, new IUiListener() {

                    @Override
                    public void onError(UiError arg0) {
                        // TODO Auto-generated method stub
                    }

                    @Override
                    public void onComplete(Object arg0) {
                        TencentHelper.refreshUserInfo(LoginActivity.this, new IUiListener() {

                            @Override
                            public void onError(UiError arg0) {
                                // TODO Auto-generated method stub

                            }

                            @Override
                            public void onComplete(Object arg0) {
                                checkUserIsRegister("1", TencentHelper.getOpenId());

                            }

                            @Override
                            public void onCancel() {
                                // TODO Auto-generated method stub

                            }
                        });
                    }

                    @Override
                    public void onCancel() {
                        // TODO Auto-generated method stub·
                    }
                });
                break;
            case R.id.login_wb:
                WbSdk.install(LoginActivity.this, new AuthInfo(LoginActivity.this, Constants.APP_KEY, Constants.REDIRECT_URL, Constants.SCOPE));
                mSsoHandler = new SsoHandler(LoginActivity.this);
                mSsoHandler.authorizeClientSso(new SelfWbAuthListener());
                break;
        }
    }

    /**
     * ****************************** 密码登录 **********************************
     */

    private void passwordLogin() {
        String phone = loginBinding.phone.getText().toString().trim();
        String password = loginBinding.password.getText().toString().trim();

        if (phone.length() < 11) {
            ToastUtils.showShort(LoginActivity.this, "手机号码不正确");
            return;
        }

        if (password.length() < 6) {
            ToastUtils.showShort(LoginActivity.this, "密码不能小于6位");
            return;
        }
        SendRequest.login(phone, password, new GenericsCallback<UserInfo>(new JsonGenericsSerializator()) {
            @Override
            public void onError(Call call, Exception e, int id) {

            }

            @Override
            public void onResponse(UserInfo response, int id) {
                if (response.getCode() == 200) {
                    MyApplication.getInstance().setUserInfo(response);
                    openActivity(MainActivity.class);
                    finish();
                } else {
                    ToastUtils.showShort(LoginActivity.this, response.getMsg());
                }
            }

        });
    }

    /**
     * ****************************** 微信登录 **********************************
     */

    private class WechatReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (null != intent) {
                String action = intent.getAction();
                if (action.equals(Config.wechat_get_token_success)) {
                    ToastUtils.showShort(getApplication(), "微信授权成功");
                    WXManager.getUserInfo(new StringCallback() {
                        @Override
                        public void onError(Call call, Exception e, int id) {
                            finish();
                        }

                        @Override
                        public void onResponse(String response, int id) {
                            try {
                                JSONObject json = new JSONObject(response);
                                String errorCode = json.optString("errcode");
                                if (!CommonUtil.isBlank(errorCode)) {
                                    ToastUtils.showShort(LoginActivity.this, "授权失败");
                                } else {
                                    String openid = json.optString("openid");
                                    String nickname = json.optString("nickname");
                                    String headimgurl = json.optString("headimgurl");
                                    String sex = json.optString("sex");
                                    String city = json.optString("city");
                                    String province = json.optString("province");
                                    checkUserIsRegister("2", openid);
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    });
                }
            }
        }
    }

    /**
     * ****************************** QQ登录 **********************************
     */

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == com.tencent.connect.common.Constants.REQUEST_LOGIN) {
            Tencent.onActivityResultData(requestCode, resultCode, data, new IUiListener() {
                @Override
                public void onComplete(Object o) {
                    TencentHelper.refreshUserInfo(LoginActivity.this, new IUiListener() {

                        @Override
                        public void onError(UiError arg0) {
                            // TODO Auto-generated method stub

                        }

                        @Override
                        public void onComplete(Object arg0) {

                        }

                        @Override
                        public void onCancel() {
                            // TODO Auto-generated method stub

                        }
                    });
                }

                @Override
                public void onError(UiError uiError) {

                }

                @Override
                public void onCancel() {

                }
            });
        } else {
            if (mSsoHandler != null) {
                mSsoHandler.authorizeCallBack(requestCode, resultCode, data);
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }


    /**
     * ****************************** 微博登录 **********************************
     */

    private SsoHandler mSsoHandler;

    private class SelfWbAuthListener implements WbAuthListener {
        @Override
        public void onSuccess(final Oauth2AccessToken token) {
            checkUserIsRegister("3", token.getToken());
        }

        @Override
        public void cancel() {
            Toast.makeText(LoginActivity.this, "取消授权", Toast.LENGTH_LONG).show();
        }

        @Override
        public void onFailure(WbConnectErrorMessage errorMessage) {
            Toast.makeText(LoginActivity.this, errorMessage.getErrorMessage(), Toast.LENGTH_LONG).show();
        }
    }


    private void checkUserIsRegister(final String third_type, final String third_value) {
        SendRequest.checkUserIsRegister(third_type, third_value, new GenericsCallback<UserInfo>(new JsonGenericsSerializator()) {
            @Override
            public void onError(Call call, Exception e, int id) {

            }

            @Override
            public void onResponse(UserInfo response, int id) {

                if (response.getCode() == 200) {
                    Log.i(TAG, "onResponse: "+response.getData().getPhone());
                    if (!CommonUtil.isBlank(response.getData().getPhone())){
                        MyApplication.getInstance().setUserInfo(response);
                        openActivity(MainActivity.class);
                        finish();
                    }else {
                        Bundle bundle = new Bundle();
                        bundle.putString("third_type", third_type);
                        bundle.putString("third_value", third_value);
                        openActivity(RegisterActivity.class, bundle);
                    }
                } else {
                    ToastUtils.showShort(LoginActivity.this, response.getMsg());
                }

//                if (response.getCode() == 200) {
//                    MyApplication.getInstance().setUserInfo(response);
//                    //openActivity(MainActivity.class);
//                    finish();
//                } else if (response.getCode() == 500) {
//                    Bundle bundle = new Bundle();
//                    bundle.putString("type", third_type);
//                    bundle.putString("type_id", third_value);
//                    openActivity(RegisterActivity.class, bundle);
//                } else {
//                    ToastUtils.showShort(LoginActivity.this, response.getMsg());
//                }
            }

        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (wxReceiver != null) {
            LocalBroadcastManager.getInstance(getApplicationContext()).unregisterReceiver(wxReceiver);
        }
        if (timer != null) {
            timer.cancel();
            timer = null;
        }
    }

}
