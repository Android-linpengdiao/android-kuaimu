package com.baselibrary.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.baselibrary.BaseApplication;

public class SharedPreferencesUtils {

    public static String SP_DATA = "sp_data";

    public static SharedPreferencesUtils mInstance;
    public static SharedPreferences sharedPreferences;

    public static SharedPreferencesUtils getInstance() {
        if (mInstance == null) {
            synchronized (SharedPreferencesUtils.class){
                if (mInstance == null) {
                    mInstance =  new SharedPreferencesUtils();
                }
            }
        }
        return mInstance;
    }

    public SharedPreferencesUtils() {
        sharedPreferences = BaseApplication.getInstance().getSharedPreferences(SP_DATA, Context.MODE_PRIVATE);
    }

    public int getUid() {
        return sharedPreferences.getInt("uid", 0);
    }

    public void setUid(int uid) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("uid", uid);
        editor.commit();
    }

    public String getCity() {
        return sharedPreferences.getString("City", "北京市");
    }

    public void setCity(String value) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("City", value);
        editor.commit();
    }

    public String getAdvertising() {
        return sharedPreferences.getString("Advertising", "");
    }

    public void setAdvertising(String value) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("Advertising", value);
        editor.commit();
    }

    public String getDownloadWidget() {
        return sharedPreferences.getString("widget", "");
    }

    public void setDownloadWidget(String widget) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("widget", widget);
        editor.commit();
    }

}
