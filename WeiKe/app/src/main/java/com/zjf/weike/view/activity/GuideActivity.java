package com.zjf.weike.view.activity;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.WindowManager;

import com.zjf.weike.R;
import com.zjf.weike.presenter.GuidePresenter;
import com.zjf.weike.view.viewimp.GuideViewImp;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GuideActivity extends BaseActivity<GuidePresenter> implements GuideViewImp {

    private List<Fragment> mFragments;

    @BindView(R.id.viewPager)
    ViewPager mViewPager;

    @Override
    public void initVariables() {
        mFragments = new ArrayList<>();
    }

    @Override
    public void initView() {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_guide);
        ButterKnife.bind(this);


    }

    @Override
    public void loaderData() {

    }


    @Override
    public GuidePresenter create() {
        return new GuidePresenter();
    }

    @Override
    public void showError(String error) {

    }
}
