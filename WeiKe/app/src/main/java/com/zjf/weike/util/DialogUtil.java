package com.zjf.weike.util;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.provider.Settings;
import android.support.v7.app.AlertDialog;

import com.zjf.weike.imp.OnPermissionResultListener;

/**
 * @author :ZJF
 * @version : 2016-12-21 上午 9:10
 */

public class DialogUtil {
    public static void showPermissionDialog(final Context context, String perName, final OnPermissionResultListener listener){
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
    }

}
