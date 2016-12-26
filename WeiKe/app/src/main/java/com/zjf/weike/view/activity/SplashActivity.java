package com.zjf.weike.view.activity;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.design.widget.CoordinatorLayout;
import android.view.WindowManager;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.zjf.weike.R;
import com.zjf.weike.impl.OnAsyncModel2SListener;
import com.zjf.weike.impl.OnAsyncModelListener;
import com.zjf.weike.impl.OnPermissionResultListener;
import com.zjf.weike.presenter.SplashPresenter;
import com.zjf.weike.util.SC;
import com.zjf.weike.util.DialogUtil;
import com.zjf.weike.util.SnackBarUtil;
import com.zjf.weike.view.activity.base.MVPActivity;
import com.zjf.weike.view.viewimp.SplashViewImp;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SplashActivity extends MVPActivity<SplashPresenter> implements SplashViewImp {

    @BindView(R.id.bg)
    ImageView mBg;
    @BindView(R.id.activity_splash)
    CoordinatorLayout mActivitySplash;

    private SharedPreferences mPreferences;

    @Override
    public void initVariables() {
        super.initVariables();
        mPreferences = getSharedPreferences(SC.CONFIG, Context.MODE_PRIVATE);
    }

    @Override
    public void initView() {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash);
        ButterKnife.bind(this);
        mBg.setAlpha(0f);
    }


    @Override
    public void setListener() {

    }


    @Override
    public void startApp(Class<? extends Activity> aClazz, int delay) {
        jumpTo(SplashActivity.this, aClazz, delay, true);
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
        mPresenter.requestVersionCode(getVersionCode());
    }

    public String getVersionCode() {
        return mPreferences.getString(SC.VERSION_CODE, SC.DEFAULT_VERSION_CODE);
    }

    @Override
    public String getIgnoreVersionCode() {
        return mPreferences.getString(SC.IGNORE_VERSION, "null");
    }

    @Override
    public void exit() {
        finish();
    }

    @Override
    public void showUpdataDialog(final String newVersionCode) {
        DialogUtil.showUpdataDialog(this, new OnAsyncModel2SListener<String, String>() {
            @Override
            public void onAction(String list) {
                mPresenter.startApp(getVersionCode());
            }

            @Override
            public void onFailure(String msg, int type) {
                mPreferences.edit().putString(SC.IGNORE_VERSION, newVersionCode).apply();
                mPresenter.startApp(getVersionCode());
            }

            @Override
            public void onSuccess(String list) {
                mPresenter.updata();
            }
        });
    }

    @Override
    public void showForceUpdataDialog() {
        DialogUtil.showForceUpataDialog(this, new OnAsyncModelListener<String>() {
            @Override
            public void onFailure(String msg, int type) {

            }

            @Override
            public void onSuccess(String list) {
                mPresenter.updata();
            }
        });
    }

    @Override
    public void setBackGround(String url) {
        Glide.with(this)
                .load(url)
                .asBitmap()
                .into(mBg);
        mBg.animate().setDuration(2000)
                .alpha(1)
                .setInterpolator(new AccelerateDecelerateInterpolator())
                .start();
    }

    @Override
    public boolean isFirstStart(String version) {
        return mPreferences.getBoolean(version, true);
    }

    @Override
    public void showSnakBar(String msg, int type) {
        SnackBarUtil.ShortSnackbar(mActivitySplash, msg, type).show();
    }

    @Override
    public SplashPresenter create() {
        return new SplashPresenter(mPermissions);
    }


}
