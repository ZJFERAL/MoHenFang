package com.zjf.weike.imp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import java.util.Timer;
import java.util.TimerTask;

/**
 * @author :ZJF
 * @version : 2016-12-20 上午 11:05
 */

public class JumpInto implements JumpTo {
    public void jumpTo(final Activity activity, final Class<? extends Activity> aClazz, int delay) {
        if (delay == 0) {
            jumpTo(activity, aClazz);
        } else {
            new Timer().schedule(new TimerTask() {
                @Override
                public void run() {
                    jumpTo(activity, aClazz);
                }
            }, delay);
        }
    }

    public void jumpTo(final Activity activity, final Class<? extends Activity> aClazz, int delay, final Bundle bundle) {
        if (delay == 0) {
            jumpTo(activity, aClazz, bundle);
        } else {
            new Timer().schedule(new TimerTask() {
                @Override
                public void run() {
                    jumpTo(activity, aClazz, bundle);
                }
            }, delay);
        }
    }


    public void jumpTo(Activity activity, Class<? extends Activity> aClazz, Bundle bundle) {
        Intent intent = new Intent(activity, aClazz);
        intent.putExtra("data", bundle);
        activity.startActivity(intent);
    }

    public void jumpTo(Activity activity, Class<? extends Activity> aClazz) {
        activity.startActivity(new Intent(activity, aClazz));
    }
}
