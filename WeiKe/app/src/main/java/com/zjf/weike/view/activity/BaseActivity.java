package com.zjf.weike.view.activity;

import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;

import com.zjf.weike.presenter.base.BasePresenter;
import com.zjf.weike.presenter.base.PresenterFactory;
import com.zjf.weike.presenter.base.PresenterLoader;

public abstract class BaseActivity<T extends BasePresenter> extends AppCompatActivity implements LoaderManager.LoaderCallbacks<T>,PresenterFactory<T> {

    protected T mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportLoaderManager().initLoader(0, null, this);
        initVariables();
        initView();
        loaderData();
    }

    /**
     * 做初始化方面的工作,比如接收上一个界面的Intent
     */
    public abstract void initVariables();

    /**
     * 初始化控件
     */
    public abstract void initView();

    /**
     * 加载数据
     */
    public abstract void loaderData();

    @Override
    public Loader<T> onCreateLoader(int id, Bundle args) {
        return new PresenterLoader<>(this, this);
    }

    @Override
    public void onLoadFinished(Loader<T> loader, T data) {
        this.mPresenter = data;
    }

    @Override
    public void onLoaderReset(Loader<T> loader) {
        this.mPresenter = null;
    }

    @Override
    protected void onStart() {
        super.onStart();
        mPresenter.onViewAttached(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        mPresenter.onViewDeached();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.onDestroyed();
    }
}
