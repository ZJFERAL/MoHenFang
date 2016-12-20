package com.zjf.weike.view.activity;

import android.Manifest;
import android.view.WindowManager;

import com.tbruyelle.rxpermissions2.Permission;
import com.zjf.weike.R;
import com.zjf.weike.presenter.SplashPresenter;
import com.zjf.weike.view.activity.base.MVPActivity;
import com.zjf.weike.view.viewimp.SplashViewImp;

import io.reactivex.functions.Consumer;

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
        mPermissions.requestEach(Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.READ_PHONE_STATE)
                .subscribe(new Consumer<Permission>() {
                    @Override
                    public void accept(Permission permission) throws Exception {
                        if (permission.granted) {

                        } else if (permission.shouldShowRequestPermissionRationale) {
                            // Denied permission without ask never again
                        } else {
                            // Denied permission with ask never again
                            // Need to go to the settings
                        }
                    }
                });

    }


    @Override
    public void setListener() {

    }


    @Override
    public void compareVersion(String version) {
        // TODO 与本地版本号比较，强制升级？进入引导页？……
    }

    @Override
    public void showSnakBar(String msg) {

    }

    @Override
    public SplashPresenter create() {
        return new SplashPresenter();
    }

}
