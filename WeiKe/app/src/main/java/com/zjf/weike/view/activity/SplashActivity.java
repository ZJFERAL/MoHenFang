package com.zjf.weike.view.activity;

import android.Manifest;
import android.view.WindowManager;

import com.tbruyelle.rxpermissions2.Permission;
import com.zjf.weike.R;
import com.zjf.weike.imp.OnPermissionResultListener;
import com.zjf.weike.presenter.SplashPresenter;
import com.zjf.weike.util.DialogUtil;
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
        requestPermission(Manifest.permission.READ_EXTERNAL_STORAGE);

    }

    //请求权限
    private void requestPermission(final String permissionName) {
        mPermissions.requestEach(permissionName)
                .subscribe(new Consumer<Permission>() {
                    @Override
                    public void accept(Permission permission) throws Exception {
                        if (permission.granted) {
                            switch (permission.name) {
                                case Manifest.permission.READ_EXTERNAL_STORAGE:
                                    requestPermission(Manifest.permission.ACCESS_COARSE_LOCATION);
                                    break;
                                case Manifest.permission.ACCESS_COARSE_LOCATION:
                                    requestPermission(Manifest.permission.READ_PHONE_STATE);
                                    break;
                                case Manifest.permission.READ_PHONE_STATE:
                                    // TODO 网络请求版本号
                                    jumpTo(SplashActivity.this, GuideActivity.class, 2000);
                                    break;
                            }
                        } else if (permission.shouldShowRequestPermissionRationale) {
                            requestPermission(permissionName);
                        } else {
                            DialogUtil.showPermissionDialog(SplashActivity.this, permissionName + getResources().getString(R.string.text_needpermission), new OnPermissionResultListener() {
                                @Override
                                public void cancel() {
                                    requestPermission(permissionName);
                                }
                            });
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
