package com.zjf.weike.presenter;

import com.zjf.weike.presenter.base.BasePresenter;
import com.zjf.weike.view.viewimp.PublishViewImp;

/**
 * @author :ZJF
 * @version : 2016-12-21 下午 5:02
 */

public class PublishPresenter implements BasePresenter {

    private PublishViewImp mView;

    public PublishPresenter(PublishViewImp view) {
        mView = view;
    }

    public void publishInfo() {
        // 上传数据
        mView.published();
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
