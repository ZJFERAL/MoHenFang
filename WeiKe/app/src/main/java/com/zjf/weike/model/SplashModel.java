package com.zjf.weike.model;

import com.zjf.weike.impl.OnAsyncModelListener;
import com.zjf.weike.model.modelimp.SplashModelImp;

/**
 * @author :ZJF
 * @version : 2016-12-24 上午 10:50
 */

public class SplashModel implements SplashModelImp {

    @Override
    public void getData(OnAsyncModelListener<String> listener) {
        listener.onSuccess("1.00");
    }
}
