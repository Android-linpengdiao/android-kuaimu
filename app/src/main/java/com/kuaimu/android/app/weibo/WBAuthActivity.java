/*
 * Copyright (C) 2010-2013 The SINA WEIBO Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.kuaimu.android.app.weibo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import android.widget.Toast;

import com.kuaimu.android.app.R;
import com.sina.weibo.sdk.auth.AccessTokenKeeper;
import com.sina.weibo.sdk.auth.Oauth2AccessToken;
import com.sina.weibo.sdk.auth.WbConnectErrorMessage;
import com.sina.weibo.sdk.auth.sso.SsoHandler;
import com.sina.weibo.sdk.exception.WeiboException;
import com.sina.weibo.sdk.net.RequestListener;

import java.text.SimpleDateFormat;

/**
 * 该类主要演示如何进行授权、SSO登陆。
 *
 * @author SINA
 * @since 2013-09-29
 */
public class WBAuthActivity extends Activity {

    private static final String TAG = "weibosdk";
    /**
     * 显示认证后的信息，如 AccessToken
     */
    private TextView mTokenText;
    /**
     * 封装了 "access_token"，"expires_in"，"refresh_token"，并提供了他们的管理功能
     */
    private Oauth2AccessToken mAccessToken;
    /**
     * 注意：SsoHandler 仅当 SDK 支持 SSO 时有效
     */
    private SsoHandler mSsoHandler;

    /**
     * @see {@link Activity#onCreate}
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weibo_auth);

        // 获取 Token View，并让提示 View 的内容可滚动（小屏幕可能显示不全）
        mTokenText = (TextView) findViewById(R.id.token_text_view);
        TextView hintView = (TextView) findViewById(R.id.obtain_token_hint);
        hintView.setMovementMethod(new ScrollingMovementMethod());
        // 创建微博实例

        mSsoHandler = new SsoHandler(WBAuthActivity.this);
        // SSO client
        findViewById(R.id.obtain_token_via_sso).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                mSsoHandler.authorizeClientSso(new SelfWbAuthListener());
            }
        });

        // SSO web
        findViewById(R.id.obtain_token_via_web).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                mSsoHandler.authorizeWeb(new SelfWbAuthListener());
            }
        });

        // SSO all
        findViewById(R.id.obtain_token_via_signature).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                mSsoHandler.authorize(new SelfWbAuthListener());
            }
        });
/*        findViewById(R.id.api_login).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                mSsoHandler.fetchGuestUserInfoAsync("hwshortvideo", "KTazRAtKBnSd05lJm6fxL4lEbfLTayyw", "1078195010", new IGuestUserInfoListener() {
                    @Override
                    public void onGuestUserInfoRetrieved(GuestUserInfo userInfo) {
                        Log.v("zxs","用户信息"+userInfo.getGsid());
                    }

                    @Override
                    public void onGuestUserInfoRetrievedFailed(WeiboException e) {

                    }
                });
            }
        });*/
        // 用户登出
        findViewById(R.id.logout).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                AccessTokenKeeper.clear(getApplicationContext());
                mAccessToken = new Oauth2AccessToken();
                updateTokenView(false);
            }
        });

        //更新token
        findViewById(R.id.refresh).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!TextUtils.isEmpty(mAccessToken.getRefreshToken())) {
                    AccessTokenKeeper.refreshToken(Constants.APP_KEY, WBAuthActivity.this, new RequestListener() {
                        @Override
                        public void onComplete(String response) {

                        }

                        @Override
                        public void onWeiboException(WeiboException e) {

                        }
                    });
                }
            }
        });

        // 从 SharedPreferences 中读取上次已保存好 AccessToken 等信息，
        // 第一次启动本应用，AccessToken 不可用
        mAccessToken = AccessTokenKeeper.readAccessToken(this);
        if (mAccessToken.isSessionValid()) {
            updateTokenView(true);
        }
    }

    /**
     * 当 SSO 授权 Activity 退出时，该函数被调用。
     *
     * @see {@link Activity#onActivityResult}
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // SSO 授权回调
        // 重要：发起 SSO 登陆的 Activity 必须重写 onActivityResults
        if (mSsoHandler != null) {
            mSsoHandler.authorizeCallBack(requestCode, resultCode, data);
        }

    }


    private class SelfWbAuthListener implements com.sina.weibo.sdk.auth.WbAuthListener {
        @Override
        public void onSuccess(final Oauth2AccessToken token) {
            WBAuthActivity.this.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    mAccessToken = token;
                    if (mAccessToken.isSessionValid()) {
                        // update Token
                        updateTokenView(false);
                        // save Token to SharedPreferences
                        AccessTokenKeeper.writeAccessToken(WBAuthActivity.this, mAccessToken);
                        Toast.makeText(WBAuthActivity.this,
                                "授权成功", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }

        @Override
        public void cancel() {
            Toast.makeText(WBAuthActivity.this,
                    "取消授权", Toast.LENGTH_LONG).show();
        }

        @Override
        public void onFailure(WbConnectErrorMessage errorMessage) {
            Toast.makeText(WBAuthActivity.this, errorMessage.getErrorMessage(), Toast.LENGTH_LONG).show();
        }
    }

    /**
     * 显示当前 Token 信息。
     *
     * @param hasExisted 配置文件中是否已存在 token 信息并且合法
     */
    private void updateTokenView(boolean hasExisted) {
        String date = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(
                new java.util.Date(mAccessToken.getExpiresTime()));
        String format = "Token：%1$s \n有效期：%2$s";
        mTokenText.setText(String.format(format, mAccessToken.getToken(), date));

        String message = String.format(format, mAccessToken.getToken(), date);
        if (hasExisted) {
            message = "Token 仍在有效期内，无需再次登录。" + "\n" + message;
        }
        mTokenText.setText(message);
    }
}