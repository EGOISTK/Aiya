package com.aiyaschool.aiya;

import android.app.Application;
import android.content.SharedPreferences;

import com.aiyaschool.aiya.bean.User;
import com.aiyaschool.aiya.message.utils.TLSService;
import com.tencent.TIMManager;

/**
 * Created by EGOISTK21 on 2017/3/15.
 */

public class MyApplication extends Application {

    private User user;
    private boolean matched;
    private static MyApplication instance;

    public void setMatched(boolean matched) {
        this.matched = matched;
    }

    public boolean isMatched() {
        return matched;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        TIMManager.getInstance().init(this);
        TLSService.getInstance().initTlsSdk(this);
        instance = this;
        setMatched(false);
    }

    public static MyApplication getInstance() {
        return instance;
    }

    private void getUserInfo() {
        SharedPreferences userInfo = getSharedPreferences("userInfo", MODE_PRIVATE);
        String userName = userInfo.getString("username", null);
        String userSig = userInfo.getString("usersig", null);
        String loginToken = userInfo.getString("logintoken", null);
    }
}
