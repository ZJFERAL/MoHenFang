package com.zjf.weike.util;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;

import com.zjf.weike.imp.OnPermissionResultListener;

/**
 * @author :ZJF
 * @version : 2016-12-20 上午 10:11
 */

public class PermissionUtil {
    /**
     * 初始化获取权限
     *
     * @param context
     * @param permissions
     * @return
     */
    public static final boolean initPermission(Context context, @NonNull String[] permissions) {
        //版本判断
        if (Build.VERSION.SDK_INT >= 23) {
            //判断是否拥有权限
            boolean isPer = false;
            for (int i = 0; i < permissions.length; i++) {
                if (context.checkSelfPermission(permissions[i]) != PackageManager.PERMISSION_GRANTED) {
                    //弹出对话框接收权限
                    if (context instanceof Activity) {
                        ((Activity) context).requestPermissions(permissions, 60);
                    }
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * 验证是否有权限
     *
     * @param context
     * @param permissions 权限列表
     * @param perName     权限名称
     * @return
     */

    public static final boolean checkPermission(final Context context, @NonNull String[] permissions, @NonNull String perName, final OnPermissionResultListener listener) {
        //版本判断
        if (Build.VERSION.SDK_INT >= 23) {
            //判断是否拥有权限
            boolean isPer = false;
            for (int i = 0; i < permissions.length; i++) {
                if (context.checkSelfPermission(permissions[i]) != PackageManager.PERMISSION_GRANTED) {
                    //弹出对话框接收权限
                    AlertDialog.Builder builder = new AlertDialog.Builder(context);
                    builder.setTitle("缺少" + perName);
                    builder.setMessage("是否去授予" + perName);
                    builder.setPositiveButton("去设置", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent intent = new Intent();
                            intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                            intent.setData(Uri.fromParts("package", context.getPackageName(), null));
                            context.startActivity(intent);
                        }
                    });
                    builder.setNegativeButton("取消", new DialogInterface.OnClickListener() { //设置取消按钮
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                            listener.cancel();
                        }
                    });
                    builder.show();
                    return false;
                }
            }
        }
        return true;
    }

    public static final boolean checkPermission(final Context context, @NonNull String[] permissions, @NonNull String perName) {
        return checkPermission(context, permissions, perName, null);
    }
}
