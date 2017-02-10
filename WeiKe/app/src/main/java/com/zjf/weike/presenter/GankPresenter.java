package com.zjf.weike.presenter;

import com.zjf.weike.bean.GankBean;
import com.zjf.weike.impl.OnAsyncModelListener;
import com.zjf.weike.model.GankModel;
import com.zjf.weike.model.modelimp.GankModelImp;
import com.zjf.weike.presenter.base.Presenter;
import com.zjf.weike.view.viewimp.GankViewImp;

/**
 * @author :ZJF
 * @version : 2016-12-17 下午 8:02
 */

public class GankPresenter extends Presenter<GankViewImp> {

    private GankModelImp mModelImp;
    private int type;
    private int currentPageIndex = 1;

    public GankPresenter(int type) {
        mModelImp = new GankModel();
        this.type = type;
    }

    @Override
    protected void onViewStart() {
        flushData();
    }

    public void flushData() {
        currentPageIndex = 1;
        mModelImp.flushData(new OnAsyncModelListener<GankBean>() {
            @Override
            public void onFailure(String msg, int type) {
                mView.showSnakBar(msg, type);
            }

            @Override
            public void onSuccess(GankBean msg) {
                mView.flushData(msg);
            }
        }, type);
    }

    public void loadMore() {
        currentPageIndex++;
        mModelImp.getMoreData(new OnAsyncModelListener<GankBean>() {
            @Override
            public void onFailure(String msg, int type) {
                mView.showSnakBar(msg, type);
            }

            @Override
            public void onSuccess(GankBean msg) {
                mView.moreData(msg);
            }
        }, type, currentPageIndex);
    }
}
