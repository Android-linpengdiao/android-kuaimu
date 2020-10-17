package com.kuaimu.android.app;


import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;

import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.baselibrary.MessageBus;
import com.baselibrary.UserInfo;
import com.baselibrary.utils.CommonUtil;
import com.baselibrary.utils.GlideLoader;
import com.baselibrary.utils.ToastUtils;
import com.kuaimu.android.app.activity.BaseActivity;
import com.kuaimu.android.app.activity.ChatActivity;
import com.kuaimu.android.app.activity.EditorActivity;
import com.kuaimu.android.app.activity.SettingsActivity;
import com.kuaimu.android.app.databinding.ActivityMainBinding;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;

public class MainActivity extends BaseActivity {

    private ActivityMainBinding mainBinding;

    private ActionBarDrawerToggle mDrawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        addActivity(this);

        mDrawerToggle = new ActionBarDrawerToggle(
                this, mainBinding.drawerLayout, null, R.string.app_name, R.string.app_name);
        mainBinding.drawerLayout.setDrawerListener(mDrawerToggle);
        mDrawerToggle.syncState();

        intHeaderView();

        EventBus.getDefault().register(this);
    }

    private ImageView userIcon;
    private TextView userName;
    private TextView userTouristId;
    private TextView userAddr;
    private TextView userLevel;
    private ImageView userVip;
    private ImageView isVip;
    private TextView fanNumber;
    private TextView followNumber;

    private void intHeaderView() {
        View headerView = mainBinding.navView.getHeaderView(0);
        View userInfoView = headerView.findViewById(R.id.userInfoView);
        userIcon = headerView.findViewById(R.id.user_icon);
        userName = headerView.findViewById(R.id.user_name);
        userTouristId = headerView.findViewById(R.id.user_tourist_id);
        userVip = headerView.findViewById(R.id.user_vip);
        View chatView = headerView.findViewById(R.id.chatView);
        View fansView = headerView.findViewById(R.id.fansView);
        View followView = headerView.findViewById(R.id.followView);
        View workView = headerView.findViewById(R.id.workView);
        View walletView = headerView.findViewById(R.id.walletView);
        View vipView = headerView.findViewById(R.id.vipView);
        View settingView = headerView.findViewById(R.id.settingView);

        chatView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getUid(true) > 0) {
//                    Intent intent = new Intent(MainActivity.this, MyWorkActivity.class);
//                    intent.putExtra("uid", getUserInfo().getData().getId());
//                    startActivity(intent);
                }
            }
        });
        workView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getUid(true) > 0) {
                    openActivity(ChatActivity.class);
                }
            }
        });
        followView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getUid(true) > 0) {
//                    openActivity(MyFollowActivity.class);
                }
            }
        });
        fansView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getUid(true) > 0) {
//                    openActivity(MyFansActivity.class);
                }
            }
        });
        walletView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getUid(true) > 0) {
//                    openActivity(MyWalletActivity.class);
                }
            }
        });
        vipView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getUid(true) > 0) {
//                    openActivity(MyVIPActivity.class);
                }
            }
        });
        settingView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivity(SettingsActivity.class);
            }
        });
        userInfoView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getUid(true) > 0) {
                    openActivity(EditorActivity.class);
                }
            }
        });
    }

    private void intHeaderData(UserInfo userInfo) {
        userName.setText(userInfo.getData().getName() + "");
        userTouristId.setText(getString(R.string.app_name) + "：" + userInfo.getData().getTourist_id());
        GlideLoader.LoderCircleImage(this, userInfo.getData().getAvatar(), userIcon);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getMainMessage(MessageBus messageBus) {
        if (messageBus.getCodeType().equals(messageBus.msgId_openDrawer)) {
            mainBinding.drawerLayout.openDrawer(Gravity.LEFT);
        }

    }

    @Override
    protected void onResume() {
        super.onResume();
        if (getUid() > 0) {
            intHeaderData(getUserInfo());
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    private long lastTime = 0;

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (System.currentTimeMillis() - lastTime > 2000) {
                lastTime = System.currentTimeMillis();
                ToastUtils.showShort(MainActivity.this, "再按一次退出应用");
                return true;
            }
        }
        return super.onKeyDown(keyCode, event);
    }

}