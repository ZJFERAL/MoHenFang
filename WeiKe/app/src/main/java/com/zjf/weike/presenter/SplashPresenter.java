package com.zjf.weike.presenter;

import android.Manifest;

import com.tbruyelle.rxpermissions2.Permission;
import com.tbruyelle.rxpermissions2.RxPermissions;
import com.zjf.weike.App;
import com.zjf.weike.R;
import com.zjf.weike.impl.OnAsyncModelListener;
import com.zjf.weike.model.SplashModel;
import com.zjf.weike.model.modelimp.SplashModelImp;
import com.zjf.weike.presenter.base.BasePresenter;
import com.zjf.weike.view.activity.GuideActivity;
import com.zjf.weike.view.activity.MainActivity;
import com.zjf.weike.view.viewimp.SplashViewImp;

import io.reactivex.functions.Consumer;

import static java.lang.Double.parseDouble;

/**
 * @author :ZJF
 * @version : 2016-12-16 下午 4:51
 */

public class SplashPresenter implements BasePresenter<SplashViewImp> {

    private SplashViewImp mView;
    private SplashModelImp mModel;
    private RxPermissions mPermissions;
    private boolean isAttached = false;

    public SplashPresenter(RxPermissions rxPermissions) {
        this.mPermissions = rxPermissions;
        mModel = new SplashModel();
    }

    @Override
    public void onViewAttached(SplashViewImp view) {
        isAttached = true;
        this.mView = view;
        mModel.getBackGround(new OnAsyncModelListener<String>() {
            @Override
            public void onFailure(String msg, int type) {

            }

            @Override
            public void onSuccess(String list) {
                if (isAttached) {
                    mView.setBackGround(list);
                }
            }
        });
        requestPermission(Manifest.permission.READ_EXTERNAL_STORAGE);
    }

    public void requestPermission(final String permissionName) {
        mPermissions.requestEach(permissionName)
                .subscribe(new Consumer<Permission>() {
                    @Override
                    public void accept(Permission permission) throws Exception {
                        if (isAttached) {
                            if (permission.granted) {
                                switch (permission.name) {
                                    case Manifest.permission.READ_EXTERNAL_STORAGE:
                                        requestPermission(Manifest.permission.ACCESS_COARSE_LOCATION);
                                        break;
                                    case Manifest.permission.ACCESS_COARSE_LOCATION:
                                        requestPermission(Manifest.permission.CAMERA);
                                        break;
                                    case Manifest.permission.CAMERA:
                                        requestPermission(Manifest.permission.READ_PHONE_STATE);
                                        break;
                                    case Manifest.permission.READ_PHONE_STATE:
                                        mView.onAllPermissionPass();
                                        break;
                                }
                            } else if (permission.shouldShowRequestPermissionRationale) {
                                requestPermission(permissionName);
                            } else {
                                mView.showPermisssionDialog(permissionName, App.getInstance().getString(R.string.text_needpermission));
                            }
                        }
                    }
                });
    }


    public void requestVersionCode(final String localCode) {
        mModel.getData(new OnAsyncModelListener<String>() {
            @Override
            public void onFailure(String msg, int type) {
                if (isAttached) {
                    mView.showSnakBar(msg, type);
                }
            }

            @Override
            public void onSuccess(String versioncode) {
                if (isAttached) {
                    if (versioncode.equals(localCode)) {
                        if (mView.isFirstStart(versioncode)) {
                            mView.startApp(GuideActivity.class, 2000);
                        } else {
                            mView.startApp(MainActivity.class, 2000);
                        }

                    } else {
                        if (parseDouble(versioncode) - parseDouble(localCode) >= 3f) {
                            mView.showForceUpdataDialog();
                        } else {
                            mView.showUpdataDialog();
                        }
                    }// # equals
                }
            }
        });
    }


    @Override
    public void onViewDeached() {
        isAttached = false;
    }

    @Override
    public void onDestroyed() {
        isAttached = false;
        mView = null;
        mModel = null;
    }
}
