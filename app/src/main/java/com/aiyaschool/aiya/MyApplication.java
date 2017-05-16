package com.aiyaschool.aiya;

import android.app.Application;
import android.util.Log;

import com.aiyaschool.aiya.bean.User;
import com.aiyaschool.aiya.util.DBUtil;
import com.aiyaschool.aiya.util.SignUtil;
import com.tencent.TIMManager;
import com.tencent.TIMOfflinePushListener;
import com.tencent.TIMOfflinePushNotification;
import com.tencent.qalsdk.sdk.MsfSdkUtils;

import org.litepal.LitePal;

import cn.smssdk.SMSSDK;

/**
 * app实体类暴露User对象，onCreate初始化各种SDK
 * Created by EGOISTK21 on 2017/3/15.
 */

public class MyApplication extends Application {

    private static final String APP_KEY = "1d3c277c6bde4";
    private static final String APP_SECRET = "3b295b8b0455bdf9c6aedf8ecc33f3cc";
    private static final int APP_ID = 1400029084;
    private static MyApplication instance;

    @Override
    public void onCreate() {
        super.onCreate();
        SMSSDK.initSDK(this, APP_KEY, APP_SECRET);
        LitePal.initialize(this);
        TIMManager.getInstance().init(this);
//        TLSService.getInstance().initTlsSdk(this);
        if (MsfSdkUtils.isMainProcess(this)) {
            Log.d("MyApplication", "main process");
            TIMManager.getInstance().setOfflinePushListener(new TIMOfflinePushListener() {
                @Override
                public void handleNotification(TIMOfflinePushNotification notification) {
                    Log.e("MyApplication", "recv offline push");
                    notification.doNotify(getApplicationContext(), R.drawable.ic_launcher);
                }
            });
        }
        DBUtil.init(this);
        instance = this;
    }

    public static MyApplication getInstance() {
        return instance;
    }

    public static void setUser(User user) {
        System.out.println("User" + user.getUsername());
        SignUtil.setUser(user);
    }

    public static User getUser() {
        return SignUtil.getUser();
    }

}
