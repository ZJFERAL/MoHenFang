package com.zjf.weike.view.activity;

import android.app.Activity;
import android.view.WindowManager;

import com.zjf.weike.R;
import com.zjf.weike.imp.OnPermissionResultListener;
import com.zjf.weike.presenter.SplashPresenter;
import com.zjf.weike.util.DialogUtil;
import com.zjf.weike.view.activity.base.MVPActivity;
import com.zjf.weike.view.viewimp.SplashViewImp;

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
    }


    @Override
    public void setListener() {

    }


    @Override
    public void startApp(Class<? extends Activity> aClazz, int delay) {
        jumpTo(SplashActivity.this, aClazz, delay);
        finish();
    }

    @Override
    public void showPermisssionDialog(final String permissionName, String msg) {
        DialogUtil.showPermissionDialog(SplashActivity.this, msg, new OnPermissionResultListener() {
            @Override
            public void cancel() {
                mPresenter.requestPermission(permissionName);
            }
        });
    }

    @Override
    public void onAllPermissionPass() {
        mPresenter.requestVersionCode("1.00");
    }

    @Override
    public void showSnakBar(String msg, int type) {

    }

    @Override
    public SplashPresenter create() {
        return new SplashPresenter(mPermissions);
    }

}
