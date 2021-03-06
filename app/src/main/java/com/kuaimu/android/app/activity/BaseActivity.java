package com.kuaimu.android.app.activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.view.Gravity;
import android.view.View;

import com.baselibrary.Constants;
import com.baselibrary.UserInfo;
import com.baselibrary.manager.LoadingManager;
import com.baselibrary.utils.CommonUtil;
import com.baselibrary.utils.MsgCache;
import com.baselibrary.utils.PermissionUtils;
import com.baselibrary.utils.StatusBarUtil;
import com.kuaimu.android.app.R;
import com.kuaimu.android.app.manager.TencentHelper;
import com.kuaimu.android.app.manager.WXManager;
import com.kuaimu.android.app.view.OnClickListener;
import com.kuaimu.android.app.view.SharePopupWindow;
import com.okhttp.SendRequest;
import com.okhttp.callbacks.GenericsCallback;
import com.okhttp.sample_okhttp.JsonGenericsSerializator;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.UiError;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;

public class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStatusBarDarkTheme(false);
    }

    public void setStatusBarDarkTheme(boolean dark) {
        if (!StatusBarUtil.setStatusBarDarkTheme(this, dark)) {
            StatusBarUtil.setStatusBarColor(this, dark ? R.color.black : R.color.white);
        }
    }

    // 5.0版本以上
    @SuppressLint("NewApi")
    public void setStatusBarHeight(View view) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            View decorView = getWindow().getDecorView();
            int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
            decorView.setSystemUiVisibility(option);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
            if (view.findViewById(R.id.status_bar) != null) {
                view.findViewById(R.id.status_bar).setVisibility(View.VISIBLE);
                int statusBarHeight = CommonUtil.getStatusBarHeight(this);
                view.findViewById(R.id.status_bar).getLayoutParams().height = statusBarHeight;
            }
        }
    }

    // 5.0版本以上
    @SuppressLint("NewApi")
    public void setCustomStatusBarHeight(View view) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            View decorView = getWindow().getDecorView();
            int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
            decorView.setSystemUiVisibility(option);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
            if (view != null) {
                view.setVisibility(View.VISIBLE);
                int statusBarHeight = CommonUtil.getStatusBarHeight(this);
                view.getLayoutParams().height = statusBarHeight;
            }
        }
    }

    protected void showLoadingDialog() {
        showLoadingDialog(null);
    }

    protected void showLoadingDialog(String msg) {
        LoadingManager.showLoadingDialog(this, msg);
    }

    protected void hideLoadingDialog() {
        LoadingManager.hideLoadingDialog(this);
    }

    public void openActivity(Class<?> mClass) {
        openActivity(mClass, null);
    }

    public void openActivity(Class<?> mClass, Bundle mBundle) {
        Intent intent = new Intent(this, mClass);
        if (mBundle != null) {
            intent.putExtras(mBundle);
        }
        startActivity(intent);
    }

    public SharePopupWindow shareView(final Activity activity, String url, final String title, final String desc, final OnClickListener onClickListener) {

        final String shareUrl = "http://share.kalao500q.com/#/?video=" + url + "&title=" + title + "&type=1";

        SharePopupWindow sharePopupWindow = new SharePopupWindow(activity);
        sharePopupWindow.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View view, Object object) {
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
        sharePopupWindow.showAtLocation(getWindow().getDecorView(), Gravity.BOTTOM, 0, 0);
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
        MsgCache.get(this).put(Constants.USER_INFO, userInfo);
    }

    public UserInfo getUserInfo() {
        UserInfo userinfo = (UserInfo) MsgCache.get(this).getAsObject(Constants.USER_INFO);
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
            boolean isAllGranted = PermissionUtils.checkPermissionAllGranted(this, type);
            if (!isAllGranted) {
                PermissionUtils.requestPermissions(this, type, code);
                return false;
            }
        }
        return true;
    }

    private static List<Activity> activityStack = new ArrayList<Activity>();

    public void addActivity(Activity aty) {
        activityStack.add(aty);
    }

    public void removeActivity(Activity aty) {
        activityStack.remove(aty);
    }

    public static void finishActivity(Class aty) {
        for (int i = 0, size = activityStack.size(); i < size; i++) {
            if (null != activityStack.get(i) && null != aty
                    && aty.getSimpleName().equals(activityStack.get(i).getClass().getSimpleName())) {
                activityStack.get(i).finish();
            }
        }
    }

    public void finishAllActivity() {
        for (int i = 0, size = activityStack.size(); i < size; i++) {
            if (null != activityStack.get(i)) {
                activityStack.get(i).finish();
            }
        }
        activityStack.clear();
    }
}
