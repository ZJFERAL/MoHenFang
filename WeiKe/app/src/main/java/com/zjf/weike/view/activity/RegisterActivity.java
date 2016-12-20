package com.zjf.weike.view.activity;

import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.TextInputLayout;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.zjf.weike.R;
import com.zjf.weike.presenter.RegisterPresenter;
import com.zjf.weike.view.activity.base.MVPActivity;
import com.zjf.weike.view.viewimp.RegisterViewImp;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RegisterActivity extends MVPActivity<RegisterPresenter> implements RegisterViewImp {


    @BindView(R.id.edit_phone)
    EditText mEditPhone;
    @BindView(R.id.textInput_phone)
    TextInputLayout mTextInputPhone;
    @BindView(R.id.btn_getVerifyCode)
    Button mBtnGetVerifyCode;
    @BindView(R.id.edit_vcode)
    EditText mEditVcode;
    @BindView(R.id.textInput_vcode)
    TextInputLayout mTextInputVcode;
    @BindView(R.id.edit_pwd)
    EditText mEditPwd;
    @BindView(R.id.textInput_pwd)
    TextInputLayout mTextInputPwd;
    @BindView(R.id.edit_repwd)
    EditText mEditRepwd;
    @BindView(R.id.textInput_repwd)
    TextInputLayout mTextInputRepwd;
    @BindView(R.id.btn_register)
    Button mBtnRegister;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.activity_register)
    CoordinatorLayout mActivityRegister;

    @Override
    public void initVariables() {
        super.initVariables();
    }

    @Override
    public void initView() {
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);
        mToolbar.setTitle("注册");
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public void setListener() {

    }


    @Override
    public RegisterPresenter create() {
        return new RegisterPresenter();
    }

    @Override
    public void showSnakBar(String msg) {

    }


    @OnClick({R.id.btn_getVerifyCode, R.id.btn_register})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_getVerifyCode:
                break;
            case R.id.btn_register:
                break;
        }
    }
}
