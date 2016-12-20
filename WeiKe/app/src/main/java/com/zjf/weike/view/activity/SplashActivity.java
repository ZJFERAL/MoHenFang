package com.zjf.weike.view.activity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.view.WindowManager;

import com.zjf.weike.R;
import com.zjf.weike.imp.OnPermissionResultListener;
import com.zjf.weike.presenter.SplashPresenter;
import com.zjf.weike.util.PermissionUtil;
import com.zjf.weike.view.activity.base.MVPActivity;
import com.zjf.weike.view.viewimp.SplashViewImp;

public class SplashActivity extends MVPActivity<SplashPresenter> implements SplashViewImp {

    private String[] mPermissions;

    @Override
    public void initVariables() {
        super.initVariables();
        mPermissions = new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE};
    }

    @Override
    public void initView() {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash);
        boolean havePermission = PermissionUtil.initPermission(this, mPermissions);
        if (!havePermission) {
            PermissionUtil.checkPermission(this, mPermissions, "", new OnPermissionResultListener() {
                @Override
                public void cancel() {
                    // TODO 网络请求版本号
                    jumpTo(SplashActivity.this, GuideActivity.class, 1000);
                }
            });
        }else {
            // TODO 网络请求版本号
            jumpTo(SplashActivity.this, GuideActivity.class, 1000);
        }

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

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            // TODO 网络请求版本号
            jumpTo(SplashActivity.this, GuideActivity.class, 1000);
        } else {
            PermissionUtil.checkPermission(this, mPermissions, "读取SD卡权限", new OnPermissionResultListener() {
                @Override
                public void cancel() {
                    // TODO 网络请求版本号
                    jumpTo(SplashActivity.this, GuideActivity.class, 1000);
                }
            });
        }

    }


}
