package com.kuaimu.android.app;

import com.baselibrary.BaseApplication;
import com.baselibrary.utils.LogUtil;
import com.dueeeke.videoplayer.ijk.IjkPlayerFactory;
import com.dueeeke.videoplayer.player.VideoViewConfig;
import com.dueeeke.videoplayer.player.VideoViewManager;
import com.kuaimu.android.app.manager.CityManager;
import com.kuaimu.android.app.model.CityData;
import com.okhttp.utils.HttpsUtils;
import com.okhttp.utils.OkHttpUtils;

import java.lang.reflect.Field;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;

public class MyApplication extends BaseApplication {

    @Override
    public void onCreate() {
        super.onCreate();

        HttpsUtils.SSLParams sslParams = HttpsUtils.getSslSocketFactory(null, null, null);
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(10000L, TimeUnit.MILLISECONDS)
                .readTimeout(10000L, TimeUnit.MILLISECONDS)
                .addInterceptor(new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
                    @Override
                    public void log(String message) {
                        LogUtil.i("message", message);
                    }
                }).setLevel(HttpLoggingInterceptor.Level.BODY))
                .hostnameVerifier(new HostnameVerifier() {
                    @Override
                    public boolean verify(String hostname, SSLSession session) {
                        return true;
                    }
                })
                .sslSocketFactory(sslParams.sSLSocketFactory, sslParams.trustManager)
                .build();
        OkHttpUtils.initClient(okHttpClient);

        //播放核心
        VideoViewConfig config = VideoViewManager.getConfig();
        try {
            Field mPlayerFactoryField = config.getClass().getDeclaredField("mPlayerFactory");
            mPlayerFactoryField.setAccessible(true);
            mPlayerFactoryField.set(config, IjkPlayerFactory.create());
        } catch (Exception e) {
            e.printStackTrace();
        }

        getAreaFirst();
    }

    public static List<CityData.FirstChildrenBean> areaList;

    public void getAreaFirst() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                areaList = CityManager.getInstance().getAreaFirst();
            }
        }).start();
    }
}
