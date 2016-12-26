package com.zjf.weike.presenter;

import com.zjf.weike.presenter.base.BasePresenter;
import com.zjf.weike.view.viewimp.LoginViewImp;

/**
 * @author :ZJF
 * @version : 2016-12-16 下午 4:41
 */

public class LoginPresenter implements BasePresenter<LoginViewImp> {
    private LoginViewImp mView;

    @Override
    public void onViewAttached(LoginViewImp view) {
        this.mView = view;
    }

    @Override
    public void onViewDeached() {

    }

    @Override
    public void onDestroyed() {

    }
}
