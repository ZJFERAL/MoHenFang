package com.zjf.weike.view.activity;

import android.content.Intent;
import android.view.WindowManager;

import com.zjf.weike.R;
import com.zjf.weike.presenter.SplashPresenter;
import com.zjf.weike.view.activity.base.MVPActivity;
import com.zjf.weike.view.viewimp.SplashViewImp;

import java.util.Timer;
import java.util.TimerTask;

public class SplashActivity extends MVPActivity<SplashPresenter> implements SplashViewImp {

    @Override
    public void initVariables() {
        super.initVariables();

    }

    @Override
    public void initView() {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash);
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                startActivity(new Intent(SplashActivity.this, GuideActivity.class));
            }
        }, 1500);
    }

    @Override
    public void setListener() {

    }


    @Override
    public void compareVersion(String version) {
        // TODO 与本地版本号比较，强制升级？进入引导页？……
    }

    @Override
    public void showError(String error) {

    }

    @Override
    public SplashPresenter create() {
        return new SplashPresenter();
    }
}
