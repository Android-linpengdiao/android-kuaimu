package com.kuaimu.android.app.activity;

import android.Manifest;
import android.content.Intent;
import androidx.databinding.DataBindingUtil;
import android.os.Handler;
import androidx.annotation.NonNull;
import android.os.Bundle;

import com.kuaimu.android.app.MainActivity;
import com.kuaimu.android.app.R;
import com.kuaimu.android.app.databinding.ActivityWelcomeBinding;

import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.EasyPermissions;

public class WelcomeActivity extends BaseActivity {
    private static final String TAG = "WelcomeActivity";
    private ActivityWelcomeBinding welcomeBinding;

    private String[] permissions = {
            Manifest.permission.CAMERA,
            Manifest.permission.RECORD_AUDIO,
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE};
    private final int requestCode = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        welcomeBinding = DataBindingUtil.setContentView(this, R.layout.activity_welcome);
        setStatusBarDarkTheme(true);

        if ((getIntent().getFlags() & Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT) != 0) {
            finish();
            return;
        }
        permissionsManager();
    }

    @AfterPermissionGranted(requestCode)
    private void permissionsManager() {
        if (EasyPermissions.hasPermissions(this, permissions)) {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent intent = new Intent(WelcomeActivity.this, MainActivity.class);
//                    if (CommonUtil.isBlank(getUserInfo().getData())) {
//                        intent = new Intent(WelcomeActivity.this, LoginActivity.class);
//                    } else {
//                        intent = new Intent(WelcomeActivity.this, MainActivity.class);
//                    }
                    startActivity(intent);
                    overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                    finish();
                }
            }, 1000);
        } else {
            EasyPermissions.requestPermissions(this, "请同意下面的权限", requestCode, permissions);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }

}
