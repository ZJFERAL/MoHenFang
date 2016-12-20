package com.zjf.weike.view.activity;

import com.zjf.weike.presenter.ReGetPasswordPresenter;
import com.zjf.weike.view.activity.base.MVPActivity;
import com.zjf.weike.view.viewimp.ReGetPasswordViewImp;

public class ReGetPasswordActivity extends MVPActivity<ReGetPasswordPresenter> implements ReGetPasswordViewImp{


    @Override
    public ReGetPasswordPresenter create() {
        return new ReGetPasswordPresenter();
    }

    @Override
    public void initView() {

    }

    @Override
    public void setListener() {

    }

    @Override
    public void showSnakBar(String msg) {

    }
}
