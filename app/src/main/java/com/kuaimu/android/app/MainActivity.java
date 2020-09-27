package com.kuaimu.android.app;


import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.KeyEvent;
import android.view.View;
import android.widget.RadioGroup;

import com.baselibrary.manager.DialogManager;
import com.baselibrary.utils.CommonUtil;
import com.baselibrary.utils.ToastUtils;
import com.cjt2325.cameralibrary.CameraActivity;
import com.cjt2325.cameralibrary.JCameraView;
import com.kuaimu.android.app.R;
import com.kuaimu.android.app.activity.BaseActivity;
import com.kuaimu.android.app.activity.EditorActivity;
import com.kuaimu.android.app.databinding.ActivityMainBinding;
import com.kuaimu.android.app.fragment.FollowFragment;
import com.kuaimu.android.app.fragment.HomeFragment;
import com.kuaimu.android.app.fragment.MessageFragment;
import com.kuaimu.android.app.fragment.MineFragment;
import com.kuaimu.android.app.utils.ViewUtils;
import com.okhttp.SendRequest;
import com.okhttp.callbacks.StringCallback;

import okhttp3.Call;

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