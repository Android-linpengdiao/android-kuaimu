package com.kuaimu.android.app.fragment;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.Gravity;
import android.view.View;

import com.baselibrary.Constants;
import com.baselibrary.UserInfo;
import com.baselibrary.utils.CommonUtil;
import com.baselibrary.utils.MsgCache;
import com.baselibrary.utils.PermissionUtils;
import com.baselibrary.utils.StatusBarUtil;
import com.kuaimu.android.app.R;
import com.kuaimu.android.app.activity.LoginActivity;
import com.kuaimu.android.app.manager.TencentHelper;
import com.kuaimu.android.app.manager.WXManager;
import com.kuaimu.android.app.view.OnClickListener;
import com.kuaimu.android.app.view.SharePopupWindow;
import com.okhttp.SendRequest;
import com.okhttp.callbacks.GenericsCallback;
import com.okhttp.sample_okhttp.JsonGenericsSerializator;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.UiError;

import okhttp3.Call;

public class BaseFragment extends Fragment {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public void setStatusBarDarkTheme(boolean dark) {
        if (!StatusBarUtil.setStatusBarDarkTheme(getActivity(), dark)) {
            StatusBarUtil.setStatusBarColor(getActivity(), dark ? R.color.black : R.color.white);
        }
    }

    // 5.0版本以上
    @SuppressLint("NewApi")
    public void setStatusBarHeight(View view) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            View decorView = getActivity().getWindow().getDecorView();
            int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
            decorView.setSystemUiVisibility(option);
            getActivity().getWindow().setStatusBarColor(Color.TRANSPARENT);
            if (view.findViewById(R.id.status_bar) != null) {
                view.findViewById(R.id.status_bar).setVisibility(View.VISIBLE);
                int statusBarHeight = CommonUtil.getStatusBarHeight(getActivity());
                view.findViewById(R.id.status_bar).getLayoutParams().height = statusBarHeight;
            }
        }
    }

    // 5.0版本以上
    @SuppressLint("NewApi")
    public void setStatusBarHeight(View view, int color) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            View decorView = getActivity().getWindow().getDecorView();
            int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
            decorView.setSystemUiVisibility(option);
            getActivity().getWindow().setStatusBarColor(Color.TRANSPARENT);
            if (view.findViewById(R.id.status_bar) != null) {
                view.findViewById(R.id.status_bar).setVisibility(View.VISIBLE);
                view.findViewById(R.id.status_bar).setBackgroundColor(color);
                int statusBarHeight = CommonUtil.getStatusBarHeight(getActivity());
                view.findViewById(R.id.status_bar).getLayoutParams().height = statusBarHeight;
            }
        }
    }

    public void openActivity(Class<?> mClass) {
        openActivity(mClass, null);
    }

    public void openActivity(Class<?> mClass, Bundle mBundle) {
        Intent intent = new Intent(getActivity(), mClass);
        if (mBundle != null) {
            intent.putExtras(mBundle);
        }
        startActivity(intent);
    }

    public SharePopupWindow shareView(final Activity activity, final String url, final String title, final String desc, final OnClickListener onClickListener) {

        final String shareUrl = "http://share.kalao500q.com/#/?video=" + url + "&title=" + title + "&type=1";
        Log.i("shareUrl", "shareView: "+shareUrl);
        SharePopupWindow sharePopupWindow = new SharePopupWindow(activity);
        sharePopupWindow.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View view, Object object) {
                if (onClickListener != null) {
                    onClickListener.onClick(view, null);
                }
                switch (view.getId()) {
                    case R.id.shareWx:
                        // scene 0代表好友   1代表朋友圈
                        WXManager.send(activity, shareUrl, title, desc, 0);

                        break;
                    case R.id.shareWxMoment:
                        WXManager.send(activity, shareUrl, title, desc, 1);

                        break;
                    case R.id.shareQQ:
                        TencentHelper.shareToQQ(activity, shareUrl, title, desc, null, new IUiListener() {
                            @Override
                            public void onComplete(Object o) {
                            }

                            @Override

                            public void onError(UiError uiError) {

                            }

                            @Override
                            public void onCancel() {

                            }
                        });

                        break;
                    case R.id.shareWeibo:
                        break;
                }
            }

            @Override
            public void onLongClick(View view, Object object) {

            }
        });
        sharePopupWindow.showAtLocation(getActivity().getWindow().getDecorView(), Gravity.BOTTOM, 0, 0);
        return sharePopupWindow;
    }

    public SharePopupWindow shareDiscoverView(final Activity activity, final String avatar, final String title, final String desc, final String img, final OnClickListener onClickListener) {

        final String shareUrl = "http://discover.kalao500q.com/#/?avatar=" + avatar + "&title=" + title + "&desc=" + desc + "&img=" + img;
        Log.i("shareUrl", "shareDiscoverView: "+shareUrl);
        SharePopupWindow sharePopupWindow = new SharePopupWindow(activity);
        sharePopupWindow.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View view, Object object) {
                if (onClickListener != null) {
                    onClickListener.onClick(view, null);
                }
                switch (view.getId()) {
                    case R.id.shareWx:
                        // scene 0代表好友   1代表朋友圈
                        WXManager.send(activity, shareUrl, title, desc, 0);

                        break;
                    case R.id.shareWxMoment:
                        WXManager.send(activity, shareUrl, title, desc, 1);

                        break;
                    case R.id.shareQQ:
                        TencentHelper.shareToQQ(activity, shareUrl, title, desc, null, new IUiListener() {
                            @Override
                            public void onComplete(Object o) {
                            }

                            @Override

                            public void onError(UiError uiError) {

                            }

                            @Override
                            public void onCancel() {

                            }
                        });

                        break;
                    case R.id.shareWeibo:
                        break;
                }
            }

            @Override
            public void onLongClick(View view, Object object) {

            }
        });
        sharePopupWindow.showAtLocation(getActivity().getWindow().getDecorView(), Gravity.BOTTOM, 0, 0);
        return sharePopupWindow;
    }


    public void baseInfo() {
        SendRequest.baseInfo(getUserInfo().getData().getId(), new GenericsCallback<UserInfo>(new JsonGenericsSerializator()) {
            @Override
            public void onError(Call call, Exception e, int id) {
            }

            @Override
            public void onResponse(UserInfo response, int id) {
                if (response.getCode() == 200 && response.getData() != null) {
                    setUserInfo(response);
                }
            }

        });
    }

    public void setUserInfo(UserInfo userInfo) {
        MsgCache.get(getActivity()).put(Constants.USER_INFO, userInfo);
    }

    public UserInfo getUserInfo() {
        UserInfo userinfo = (UserInfo) MsgCache.get(getActivity()).getAsObject(Constants.USER_INFO);
        if (!CommonUtil.isBlank(userinfo)) {
            return userinfo;
        }
        return new UserInfo();
    }

    public int getUid() {
        return getUid(false);
    }

    public int getUid(boolean login) {
        if (getUserInfo().getData() != null) {
            return getUserInfo().getData().getId();
        } else {
            if (login) {
                openActivity(LoginActivity.class);
            }
            return 0;
        }
    }

    public boolean checkPermissionsAll(String type, int code) {
        if (Build.VERSION.SDK_INT >= 23) {
            boolean isAllGranted = PermissionUtils.checkPermissionAllGranted(getActivity(), type);
            if (!isAllGranted) {
                PermissionUtils.requestPermissions(getActivity(), type, code);
                return false;
            }
        }
        return true;
    }
}
