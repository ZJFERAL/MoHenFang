package com.zjf.weike.view.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.zjf.weike.R;
import com.zjf.weike.adapter.CRecyclerViewAdapter;
import com.zjf.weike.adapter.CRecyclerViewViewHolder;
import com.zjf.weike.adapter.SelectAblumAdapter;
import com.zjf.weike.adapter.SelectPictureAdapter;
import com.zjf.weike.bean.ImageFolder;
import com.zjf.weike.imp.OnPictureSelectListener;
import com.zjf.weike.presenter.SelectPicturePresenter;
import com.zjf.weike.util.SnackBarUtil;
import com.zjf.weike.view.activity.base.MVPActivity;
import com.zjf.weike.view.viewimp.SelectPictureViewImp;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SelectPictureActivity extends MVPActivity<SelectPicturePresenter> implements SelectPictureViewImp {


    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.recyclerView_picture)
    RecyclerView mRecyclerViewPicture;
    @BindView(R.id.recyclerView_folder)
    RecyclerView mRecyclerViewFolder;
    @BindView(R.id.bottom_sheet)
    NestedScrollView mBottomSheet;
    @BindView(R.id.fab)
    FloatingActionButton mFab;
    @BindView(R.id.fab_done)
    FloatingActionButton mFab_done;
    @BindView(R.id.activity_select_picture)
    CoordinatorLayout mActivitySelectPicture;

    private ProgressDialog mDialog;
    private BottomSheetBehavior mBehavior;
    private SelectPictureAdapter mPictureAdapter;
    private SelectAblumAdapter mAblumAdapter;
    private List<String> mPictures;
    private List<ImageFolder> mFolders;


    @Override
    public void initVariables() {
        super.initVariables();
        mDialog = new ProgressDialog(this);
        mDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        mDialog.setMessage(getString(R.string.progressdiolog_p));
        mDialog.setTitle(getString(R.string.hint));
        mDialog.show();
        mPictures = new ArrayList<>();
        mFolders = new ArrayList<>();
        mPictureAdapter = new SelectPictureAdapter(this, mPictures, R.layout.select_photo);
        mAblumAdapter = new SelectAblumAdapter(this, mFolders, R.layout.select_folder);
    }

    @Override
    public void initView() {
        setContentView(R.layout.activity_select_picture);
        ButterKnife.bind(this);
        mToolbar.setTitle(getString(R.string.selectpicture));
        mToolbar.setSubtitle(getString(R.string.selectnum));
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mBehavior = BottomSheetBehavior.from(mBottomSheet);
        RecyclerView.LayoutManager pictureManager = new GridLayoutManager(this, 3);
        RecyclerView.LayoutManager ablumManager = new LinearLayoutManager(this);
        mRecyclerViewPicture.setLayoutManager(pictureManager);
        mRecyclerViewPicture.setAdapter(mPictureAdapter);
        mRecyclerViewFolder.setLayoutManager(ablumManager);
        mRecyclerViewFolder.setAdapter(mAblumAdapter);

    }

    @Override
    public void setListener() {
        mPictureAdapter.setListener(new OnPictureSelectListener() {
            @Override
            public void pictureSelect(int num) {
                getSupportActionBar()
                        .setSubtitle(getString(R.string.selectnum_hint)
                                + num + getString(R.string.selectnum_half));
            }
        });
        mPictureAdapter.setOnItemClickListener(new CRecyclerViewAdapter.OnItemClickListener<String>() {
            @Override
            public void onItemClick(View view, CRecyclerViewViewHolder holder, String item) {

            }

        });
        mAblumAdapter.setOnItemClickListener(new CRecyclerViewAdapter.OnItemClickListener<ImageFolder>() {

            @Override
            public void onItemClick(View view, CRecyclerViewViewHolder holder, ImageFolder item) {
                String dir = item.getDir();
                mPresenter.getPicture(dir);
                mDialog.show();
            }
        });
    }

    @Override
    public void setPicture(List<String> pictures) {
        mDialog.dismiss();
        mPictureAdapter.flushData(pictures);
    }

    @Override
    public void setFolder(List<ImageFolder> folders) {
        if (folders != null && folders.size() != 0) {
            mAblumAdapter.flushData(folders);
        } else {
            showSnakBar(getString(R.string.nopicture), 1);
            mDialog.dismiss();
        }
    }

    @Override
    public void showSnakBar(String msg, int type) {
        if (type == 3) {
            mDialog.dismiss();
        }
        SnackBarUtil.ShortSnackbar(mActivitySelectPicture, msg, type);
    }

    @Override
    public SelectPicturePresenter create() {
        return new SelectPicturePresenter(this);
    }


    @Override
    public void onPause() {
        super.onPause();
        mDialog.dismiss();
    }


    @OnClick({R.id.fab, R.id.fab_done})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.fab:
                if (mBehavior.getState() == BottomSheetBehavior.STATE_EXPANDED) {
                    mBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                } else {
                    mBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                }
                break;
            case R.id.fab_done:
                Intent intent = new Intent();
                intent.putStringArrayListExtra("picture", mPictureAdapter.getSelectList());
                setResult(RESULT_OK, intent);
                finish();
                break;
        }
    }
}
