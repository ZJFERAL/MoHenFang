package com.zjf.weike.presenter;

import com.zjf.weike.bean.ImageFolder;
import com.zjf.weike.impl.OnAsyncModelListener;
import com.zjf.weike.model.SelectPictureAsyncModel;
import com.zjf.weike.model.modelimp.SelectPictureAsyncModelImp;
import com.zjf.weike.presenter.base.BasePresenter;
import com.zjf.weike.view.viewimp.SelectPictureViewImp;

import java.util.List;

/**
 * @author :ZJF
 * @version : 2016-12-22 下午 3:19
 */

public class SelectPicturePresenter implements BasePresenter {

    private boolean isAttach = false;
    private SelectPictureViewImp mView;
    private SelectPictureAsyncModelImp mModel;

    public SelectPicturePresenter(SelectPictureViewImp view) {
        mView = view;
        mModel = new SelectPictureAsyncModel();
    }

    public void getPicture(String folderName) {
        mModel.getPicture(folderName, new OnAsyncModelListener<List<String>>() {

            @Override
            public void onFailure(String msg,int type) {
                if (isAttach) {
                    mView.showSnakBar(msg, type);
                }
            }

            @Override
            public void onSuccess(List<String> list) {
                if (isAttach) {
                    mView.setPicture(list);
                }
            }
        });
    }

    @Override
    public void onViewAttached(final Object view) {
        isAttach = true;
        mModel.getData(new OnAsyncModelListener<List<ImageFolder>>() {

            @Override
            public void onFailure(String msg,int type) {
                if (isAttach) {
                    mView.showSnakBar(msg, type);
                }
            }

            @Override
            public void onSuccess(List<ImageFolder> list) {
                if (isAttach) {
                    mView.setFolder(list);
                }
            }
        });
    }

    @Override
    public void onViewDeached() {
        isAttach = false;
    }

    @Override
    public void onDestroyed() {
        isAttach = false;
        mView = null;
        mModel = null;
    }
}
