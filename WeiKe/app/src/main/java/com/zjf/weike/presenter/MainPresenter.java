package com.zjf.weike.presenter;

import android.content.Context;

import com.zjf.weike.R;
import com.zjf.weike.presenter.base.BasePresenter;
import com.zjf.weike.view.viewimp.MainViewImp;

/**
 * @author :ZJF
 * @version : 2016-12-19 下午 8:25
 */

public class MainPresenter implements BasePresenter {

    private long currentTime = 0;
    private MainViewImp mView;

    public MainPresenter(MainViewImp view) {
        mView = view;
    }


    public void judgeExit(Context context) {
        long timeMillis = System.currentTimeMillis();
        if (timeMillis - 2000 > currentTime) {
            mView.showSnakBar(context.getResources().getString(R.string.pressTwiceExit), 2);
            currentTime = timeMillis;
        } else {
            mView.exit();
        }
    }

    @Override
    public void onViewAttached(Object view) {

    }

    @Override
    public void onViewDeached() {

    }

    @Override
    public void onDestroyed() {

    }
}
