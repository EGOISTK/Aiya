package com.aiyaschool.aiya.love.matched.today;

import com.aiyaschool.aiya.bean.HttpResult;
import com.aiyaschool.aiya.bean.Task;
import com.aiyaschool.aiya.util.APIUtil;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by EGOISTK21 on 2017/5/25.
 */

class MatchedTodayModel implements MatchedTodayContract.Model {

//    @Override
//    public void loadIntimacy(String loveid, Observer<Intimacy> observer) {
//        APIUtil.getIntimacyApi()
//                .getIntimacy(loveid)
//                .debounce(APIUtil.FILTER_TIMEOUT, TimeUnit.SECONDS)
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .unsubscribeOn(Schedulers.io())
//                .subscribe(observer);
//    }

    @Override
    public void loadTodayTask(int period, Observer<HttpResult<Task>> observer) {
        APIUtil.getCtodayApi()
                .getToadyTask(period)
                .debounce(APIUtil.FILTER_TIMEOUT, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .unsubscribeOn(Schedulers.io())
                .subscribe(observer);
    }
}
