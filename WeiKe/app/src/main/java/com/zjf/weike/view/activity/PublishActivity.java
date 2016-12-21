package com.zjf.weike.view.activity;

import android.graphics.Bitmap;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TextInputLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.zjf.weike.R;
import com.zjf.weike.adapter.PhotoAdapter;
import com.zjf.weike.presenter.PublishPresenter;
import com.zjf.weike.util.SnackBarUtil;
import com.zjf.weike.view.activity.base.MVPActivity;
import com.zjf.weike.view.viewimp.PublishViewImp;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class PublishActivity extends MVPActivity<PublishPresenter> implements PublishViewImp {


    @BindView(R.id.edit_msg)
    EditText mEditMsg;
    @BindView(R.id.textInput_msg)
    TextInputLayout mTextInputMsg;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.activity_publish)
    CoordinatorLayout mActivityPublish;
    @BindView(R.id.fab_publish)
    FloatingActionButton mBtnPublish;
    @BindView(R.id.btn_addPhoto)
    Button mBtnAddPhoto;
    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;

    private List<Bitmap> mBitmaps;
    private PhotoAdapter mAdapter;
    private RecyclerView.LayoutManager mManager;

    @Override
    public PublishPresenter create() {
        return new PublishPresenter(this);
    }

    @Override
    public void initVariables() {
        super.initVariables();
        mBitmaps = new ArrayList<>();
        mAdapter = new PhotoAdapter(this, mBitmaps, R.layout.publish_photo);
        mManager = new GridLayoutManager(this, 3);
    }

    @Override
    public void initView() {
        setContentView(R.layout.activity_publish);
        ButterKnife.bind(this);
        mToolbar.setTitle(getResources().getString(R.string.publishdynamic));
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mRecyclerView.setLayoutManager(mManager);
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void setListener() {

    }

    @Override
    public void showSnakBar(String msg,int type) {
        SnackBarUtil.ShortSnackbar(mActivityPublish, msg, type).show();
    }


    @OnClick({R.id.fab_publish, R.id.btn_addPhoto})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.fab_publish:
                // TODO 上传数据参数
                mPresenter.publishInfo();
                break;
            case R.id.btn_addPhoto:
                // TODO 添加图片
                break;
        }
    }

    @Override
    public void published() {
        jumpTo(PublishActivity.this, MainActivity.class);
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.publish_location, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        //
        showSnakBar("location",1);
        return super.onOptionsItemSelected(item);
    }
}
