package com.zjf.weike;

import android.app.Application;

import com.zjf.weike.util.LogUtil;

/**
 * @author :ZJF
 * @version : 2016-12-16 上午 9:58
 */

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        LogUtil.isDebug = true;

    }
}
