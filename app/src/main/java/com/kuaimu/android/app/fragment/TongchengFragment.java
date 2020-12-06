package com.kuaimu.android.app.fragment;

import android.content.Intent;

import androidx.databinding.DataBindingUtil;

import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.baselibrary.Constants;
import com.baselibrary.utils.CommonUtil;
import com.baselibrary.utils.LogUtil;
import com.baselibrary.utils.PermissionUtils;
import com.baselibrary.utils.SharedPreferencesUtils;
import com.baselibrary.utils.ToastUtils;
import com.kuaimu.android.app.R;
import com.kuaimu.android.app.activity.WorkDetailActivity;
import com.kuaimu.android.app.adapter.RecommendAdapter;
import com.kuaimu.android.app.databinding.FragmentTongchengBinding;
import com.kuaimu.android.app.model.BaseData;
import com.kuaimu.android.app.model.RecommendData;
import com.kuaimu.android.app.utils.SpringViewNewFooter;
import com.kuaimu.android.app.utils.SpringViewNewHeader;
import com.kuaimu.android.app.view.GridItemDecoration;
import com.kuaimu.android.app.view.OnClickListener;
import com.liaoinstan.springview.widget.SpringView;
import com.okhttp.SendRequest;
import com.okhttp.callbacks.GenericsCallback;
import com.okhttp.sample_okhttp.JsonGenericsSerializator;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import okhttp3.Call;

public class TongchengFragment extends BaseFragment implements View.OnClickListener, AMapLocationListener {

    private static final String TAG = "TongchengFragment";
    private FragmentTongchengBinding binding;
    private RecommendAdapter adapter;
    private RecommendData recommendData;
    private boolean isRefresh = true;

    private static final int REQUEST_LOCATION = 100;
    private static final int REQUEST_LOCATION_SETTING = 200;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_tongcheng, container, false);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) binding.viewLayout.getLayoutParams();
            layoutParams.topMargin = CommonUtil.getStatusBarHeight(getActivity()) + CommonUtil.dip2px(getActivity(), 60);
        }

        adapter = new RecommendAdapter(getActivity());
        binding.recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        GridItemDecoration.Builder builder = new GridItemDecoration.Builder(getActivity());
        builder.color(R.color.transparent);
        builder.size(CommonUtil.dip2px(getActivity(), 1));
        binding.recyclerView.addItemDecoration(new GridItemDecoration(builder));
        binding.recyclerView.setAdapter(adapter);
        adapter.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view, Object object) {
                if (recommendData != null) {
                    Intent intent = new Intent(getActivity(), WorkDetailActivity.class);
                    BaseData baseData = new BaseData();
                    baseData.setData(adapter.getList());
                    intent.putExtra("baseData", baseData);
                    intent.putExtra("position", (int) object);
                    getActivity().startActivity(intent);
                }
            }

            @Override
            public void onLongClick(View view, Object object) {

            }
        });


        binding.springMine.setHeader(new SpringViewNewHeader(getContext()));
        binding.springMine.setFooter(new SpringViewNewFooter(getContext()));
        binding.springMine.setType(SpringView.Type.FOLLOW);
        binding.springMine.setListener(new SpringView.OnFreshListener() {
            @Override
            public void onRefresh() {
                isRefresh = true;
                initData(Constants.perPage, 1);
            }

            @Override
            public void onLoadmore() {
                if (recommendData != null &&recommendData.getCode() == 200 && recommendData.getData() != null &&
                        recommendData.getData().getCurrent_page() < recommendData.getData().getLast_page()) {
                    isRefresh = false;
                    initData(Constants.perPage, recommendData.getData().getCurrent_page() + 1);
                } else {
                    binding.springMine.onFinishFreshAndLoad();
                    ToastUtils.showShort(getActivity(), "没有更多了");
                }
            }
        });

        return binding.getRoot();
    }

    @Override
    public void onResume() {
        super.onResume();
        if (recommendData != null && recommendData.getCode() == 200 && recommendData.getData() != null ) {
            isRefresh = true;
            initData(adapter.getList().size(), 1);
        } else {
            isRefresh = true;
            initData(Constants.perPage, 1);
        }
    }

    private void initData(int perPage, int currentPage) {
        SendRequest.videoCity(getUid(),SharedPreferencesUtils.getInstance().getCity(), perPage, currentPage, new GenericsCallback<RecommendData>(new JsonGenericsSerializator()) {
            @Override
            public void onError(Call call, Exception e, int id) {
                binding.springMine.onFinishFreshAndLoad();
            }

            @Override
            public void onResponse(RecommendData response, int id) {
                binding.springMine.onFinishFreshAndLoad();
                if (response.getCode() == 200 && response.getData() != null && response.getData().getData() != null) {
                    recommendData = response;
                    if (isRefresh) {
                        adapter.refreshData(response.getData().getData());
                    } else {
                        adapter.loadMoreData(response.getData().getData());
                    }
                }
            }

        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
        }
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
                case 100:

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
