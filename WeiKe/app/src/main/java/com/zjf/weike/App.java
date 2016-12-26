package com.zjf.weike;

import android.app.Application;

import com.umeng.analytics.MobclickAgent;
import com.zjf.weike.util.LogUtil;
import com.zjf.weike.util.NativeUtil;

/**
 * @author :ZJF
 * @version : 2016-12-16 上午 9:58
 */

public class App extends Application {

    private static App instance;
    public static final String CONFIG = "config";

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;

        LogUtil.isDebug = true;
        String key = NativeUtil.getKey();
        MobclickAgent.startWithConfigure(
                new MobclickAgent.UMAnalyticsConfig(this,
                        key, "wandoujia", MobclickAgent.EScenarioType.E_UM_NORMAL));
    }

    public static App getInstance() {
        return instance;
    }
}
