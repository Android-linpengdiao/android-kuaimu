package com.kuaimu.android.app.fragment;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.baselibrary.MessageBus;
import com.baselibrary.utils.CommonUtil;
import com.baselibrary.utils.LogUtil;
import com.baselibrary.utils.PermissionUtils;
import com.baselibrary.utils.SharedPreferencesUtils;
import com.baselibrary.utils.StatusBarUtil;
import com.cjt2325.cameralibrary.CameraActivity;
import com.cjt2325.cameralibrary.JCameraView;
import com.kuaimu.android.app.R;
import com.kuaimu.android.app.activity.CitySelectionActivity;
import com.kuaimu.android.app.activity.SearchActivity;
import com.kuaimu.android.app.adapter.PagerAdapter;
import com.kuaimu.android.app.databinding.FragmentHomeBinding;
import com.kuaimu.android.app.view.TabLayout;

import org.greenrobot.eventbus.EventBus;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import androidx.viewpager.widget.ViewPager;

public class MainFragment extends BaseFragment implements View.OnClickListener, ViewPager.OnPageChangeListener, AMapLocationListener {

    private static final String TAG = "MainFragment";
    private FragmentHomeBinding binding;
    private PagerAdapter mainHomePagerAdapter;

    private static final int REQUEST_CITY = 100;
    private static final int REQUEST_LOCATION = 200;
    private static final int REQUEST_LOCATION_SETTING = 300;
    private static final int REQUEST_WXCAMERA = 400;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false);
        setStatusBarHeight(binding.getRoot(), getResources().getColor(R.color.white));

        binding.userIconView.setOnClickListener(this);
        binding.searchImageView.setOnClickListener(this);
        binding.videoImageView.setOnClickListener(this);

        mainHomePagerAdapter = new PagerAdapter(getChildFragmentManager());
        mainHomePagerAdapter.addFragment("同城", new TongchengFragment());
        mainHomePagerAdapter.addFragment("关注", new RecommendFragment());
        mainHomePagerAdapter.addFragment("一线", new DiscoverFragment());
        binding.viewPager.setAdapter(mainHomePagerAdapter);
        binding.viewPager.setOffscreenPageLimit(3);
        binding.viewPager.setCurrentItem(0);
        binding.tabLayout.setupWithViewPager(binding.viewPager);
        binding.viewPager.addOnPageChangeListener(this);
        binding.tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
//                if (tab.getPosition()==0){
//                    Intent intent = new Intent(getActivity(), CitySelectionActivity.class);
//                    startActivityForResult(intent, REQUEST_CITY);
//                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {


            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                if (tab.getPosition() == 0) {
                    Intent intent = new Intent(getActivity(), CitySelectionActivity.class);
                    startActivityForResult(intent, REQUEST_CITY);
                }

            }
        });


        return binding.getRoot();
    }


    @Override
    public void onResume() {
        super.onResume();
        Log.i(TAG, "onResume: ");
//        if (getUid() > 0) {
//            GlideLoader.LoderCircleImage(getActivity(), getUserInfo().getData().getAvatar(), binding.userIconView);
//        } else {
//            GlideLoader.LoderCircleImage(getActivity(), "", binding.userIconView);
//        }
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        Log.i(TAG, "onHiddenChanged: ");
//        if (getUid() > 0) {
//            GlideLoader.LoderCircleImage(getActivity(), getUserInfo().getData().getAvatar(), binding.userIconView);
//        } else {
//            GlideLoader.LoderCircleImage(getActivity(), "", binding.userIconView);
//        }
    }

    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()) {
            case R.id.userIconView:
                MessageBus.Builder builder = new MessageBus.Builder();
                MessageBus messageBus = builder
                        .codeType(MessageBus.msgId_openDrawer)
                        .build();
                EventBus.getDefault().post(messageBus);
                break;
            case R.id.searchImageView:
                intent = new Intent(getActivity(), SearchActivity.class);
                startActivity(intent);
                break;
            case R.id.videoImageView:
                int type = JCameraView.BUTTON_STATE_ONLY_RECORDER;
                int minTime = 5;
                int maxTime = 180;
                CameraActivity.startCameraActivity(getActivity(), minTime, maxTime, "#44bf19", type, REQUEST_WXCAMERA);
                break;
        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }


    @Override
    public void onPageSelected(int position) {
        binding.statusBar.setBackgroundColor(position == 2 ? Color.TRANSPARENT : getResources().getColor(R.color.white));
        binding.topView.setBackgroundColor(position == 2 ? getResources().getColor(R.color.transparent) : getResources().getColor(R.color.white));
        StatusBarUtil.setStatusBarDarkTheme(getActivity(), position == 2 ? false : true);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            binding.userIconView.setImageTintList(ColorStateList.valueOf(ContextCompat.getColor(getActivity(), position == 2 ? R.color.white : R.color.black)));
            binding.searchImageView.setImageTintList(ColorStateList.valueOf(ContextCompat.getColor(getActivity(), position == 2 ? R.color.white : R.color.black)));
            binding.videoImageView.setImageTintList(ColorStateList.valueOf(ContextCompat.getColor(getActivity(), position == 2 ? R.color.white : R.color.black)));
            binding.tabLayout.setSelectedTabIndicatorColor(position == 2 ? getResources().getColor(R.color.white) : getResources().getColor(R.color.black));
            binding.tabLayout.setTabTextColors(Color.parseColor("#CCCCCC"), position == 2 ? Color.parseColor("#FFFFFF") : Color.parseColor("#333333"));
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    private AMapLocationClient mlocationClient;
    //声明mLocationOption对象
    public AMapLocationClientOption mLocationOption = null;

    private void initLocation() {
        mlocationClient = new AMapLocationClient(getActivity());
        //初始化定位参数
        mLocationOption = new AMapLocationClientOption();
        //设置返回地址信息，默认为true
        mLocationOption.setNeedAddress(true);
        //设置定位监听
        mlocationClient.setLocationListener(this);
        //设置定位模式为高精度模式，Battery_Saving为低功耗模式，Device_Sensors是仅设备模式
        mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
        //设置定位间隔,单位毫秒,默认为2000ms
        mLocationOption.setInterval(2000);
        mLocationOption.setOnceLocation(true);
        //设置定位参数
        mlocationClient.setLocationOption(mLocationOption);
        // 此方法为每隔固定时间会发起一次定位请求，为了减少电量消耗或网络流量消耗，
        // 注意设置合适的定位时间的间隔（最小间隔支持为1000ms），并且在合适时间调用stopLocation()方法来取消定位请求
        // 在定位结束后，在合适的生命周期调用onDestroy()方法
        // 在单次定位情况下，定位无论成功与否，都无需调用stopLocation()方法移除请求，定位sdk内部会移除
        // 启动定位
        mlocationClient.startLocation();
    }

    @Override
    public void onLocationChanged(AMapLocation amapLocation) {
        if (amapLocation != null) {
            if (amapLocation.getErrorCode() == 0) {
                //定位成功回调信息，设置相关消息
                amapLocation.getLocationType();//获取当前定位结果来源，如网络定位结果，详见定位类型表
                amapLocation.getLatitude();//获取纬度
                amapLocation.getLongitude();//获取经度
                amapLocation.getAccuracy();//获取精度信息
//                binding.contentScrolling.cityView.setText(amapLocation.getCity());
                SharedPreferencesUtils.getInstance().setCity(amapLocation.getCity());
                LogUtil.e(TAG, "onLocationChanged: " + amapLocation.getCity());
            } else {
                //显示错误信息ErrCode是错误码，errInfo是错误信息，详见错误码表。
                LogUtil.e(TAG, "AmapError: " + "location Error, ErrCode:"
                        + amapLocation.getErrorCode() + ", errInfo:"
                        + amapLocation.getErrorInfo());
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        boolean granted = true;
        switch (requestCode) {
            case REQUEST_LOCATION:
                for (int i = 0; i < PermissionUtils.location.length; i++) {
                    if (grantResults[i] != PackageManager.PERMISSION_GRANTED) {
                        granted = false;
                        break;
                    }
                }
                if (granted) {
                    initLocation();
                }
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == getActivity().RESULT_OK) {
            switch (requestCode) {
                case REQUEST_CITY:
                    if (data != null) {
                        String addr = data.getStringExtra("userAddr");
                        SharedPreferencesUtils.getInstance().setCity(addr);
                    }
                    break;
            }
        } else {
            switch (requestCode) {
                case REQUEST_LOCATION_SETTING:
                    if (CommonUtil.isLocationEnabled(getActivity())) {
                        initLocation();
                    }
                    break;
            }
        }
    }
}
