package com.kuaimu.android.app;


import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;

import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.baselibrary.MessageBus;
import com.baselibrary.UserInfo;
import com.baselibrary.utils.GlideLoader;
import com.baselibrary.utils.ToastUtils;
import com.kuaimu.android.app.activity.BaseActivity;
import com.kuaimu.android.app.activity.ChatListActivity;
import com.kuaimu.android.app.activity.EditorActivity;
import com.kuaimu.android.app.activity.MerchantActivity;
import com.kuaimu.android.app.activity.MessageActivity;
import com.kuaimu.android.app.activity.MineFansActivity;
import com.kuaimu.android.app.activity.MineFollowActivity;
import com.kuaimu.android.app.activity.MineLikeActivity;
import com.kuaimu.android.app.activity.MineWorkActivity;
import com.kuaimu.android.app.activity.MyPointActivity;
import com.kuaimu.android.app.activity.MyWalletActivity;
import com.kuaimu.android.app.activity.PersonAuthActivity;
import com.kuaimu.android.app.activity.SettingsActivity;
import com.kuaimu.android.app.databinding.ActivityMainBinding;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

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
    private TextView merchantAuthView;

    private void intHeaderView() {
        View headerView = mainBinding.navView.getHeaderView(0);
        View userInfoView = headerView.findViewById(R.id.userInfoView);
        userIcon = headerView.findViewById(R.id.user_icon);
        userName = headerView.findViewById(R.id.user_name);
        userTouristId = headerView.findViewById(R.id.user_tourist_id);
        userVip = headerView.findViewById(R.id.user_vip);
        View likeView = headerView.findViewById(R.id.likeView);
        View messageView = headerView.findViewById(R.id.messageView);
        View chatView = headerView.findViewById(R.id.chatView);
        View fansView = headerView.findViewById(R.id.fansView);
        View followView = headerView.findViewById(R.id.followView);
        View workView = headerView.findViewById(R.id.workView);
        View walletView = headerView.findViewById(R.id.walletView);
        View personAuthView = headerView.findViewById(R.id.personAuthView);
        merchantAuthView = headerView.findViewById(R.id.merchantAuthView);
        View myPointView = headerView.findViewById(R.id.myPointView);
        View settingView = headerView.findViewById(R.id.settingView);

        likeView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getUid(true) > 0) {
                    Bundle bundle = new Bundle();
                    bundle.putInt("uid", getUid());
                    openActivity(MineLikeActivity.class, bundle);
                }
            }
        });
        messageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getUid(true) > 0) {
                    openActivity(MessageActivity.class);
                }
            }
        });
        chatView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getUid(true) > 0) {
                    openActivity(ChatListActivity.class);
                }
            }
        });
        workView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getUid(true) > 0) {
                    openActivity(MineWorkActivity.class);
                }
            }
        });
        followView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getUid(true) > 0) {
                    Bundle bundle = new Bundle();
                    bundle.putInt("uid", getUid());
                    openActivity(MineFollowActivity.class, bundle);
                }
            }
        });
        fansView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getUid(true) > 0) {
                    Bundle bundle = new Bundle();
                    bundle.putInt("uid", getUid());
                    openActivity(MineFansActivity.class, bundle);
                }
            }
        });
        walletView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getUid(true) > 0) {
                    openActivity(MyWalletActivity.class);
                }
            }
        });
        personAuthView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getUid(true) > 0) {
                    Bundle bundle = new Bundle();
                    bundle.putInt("auth", 2);
                    openActivity(PersonAuthActivity.class, bundle);
                }
            }
        });
        merchantAuthView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getUid(true) > 0) {
                    //1通过 2正在审核 3审核未通过 4未认证
                    if (getUserInfo().getData() != null && getUserInfo().getData().getBusiness_auth_status() == 4) {
                        Bundle bundle = new Bundle();
                        bundle.putInt("auth", 1);
                        openActivity(PersonAuthActivity.class, bundle);

                    } else {
                        Bundle bundle = new Bundle();
                        bundle.putInt("auth", 1);
                        bundle.putInt("uid", getUid());
                        openActivity(MerchantActivity.class, bundle);

                    }
                }
            }
        });
        myPointView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getUid(true) > 0) {
                    openActivity(MyPointActivity.class);
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
        //1通过 2正在审核 3审核未通过 4未认证
        if (userInfo.getData().getBusiness_auth_status() != 4) {
            merchantAuthView.setText("商家广告页");
        }
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