package com.zjf.weike.view.activity;

import com.zjf.weike.R;
import com.zjf.weike.presenter.RegisterPresenter;
import com.zjf.weike.view.viewimp.RegisterViewImp;

public class RegisterActivity extends BaseActivity<RegisterPresenter> implements RegisterViewImp {


    @Override
    public void initVariables() {

    }

    @Override
    public void initView() {
        setContentView(R.layout.activity_register);
    }

    @Override
    public void loaderData() {

    }

    @Override
    public RegisterPresenter create() {
        return new RegisterPresenter();
    }
}
