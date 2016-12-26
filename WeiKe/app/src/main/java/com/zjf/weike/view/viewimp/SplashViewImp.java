package com.zjf.weike.view.viewimp;

import android.app.Activity;

/**
 * @author :ZJF
 * @version : 2016-12-15 下午 4:57
 */

public interface SplashViewImp extends BaseViewImp {

    void startApp(Class<? extends Activity> aClazz, int delay);

    void showPermisssionDialog(String permissionName, String msg);

    void onAllPermissionPass();

    void showUpdataDialog();

    void showForceUpdataDialog();

}
