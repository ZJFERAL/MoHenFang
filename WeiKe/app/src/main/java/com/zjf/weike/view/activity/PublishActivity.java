package com.zjf.weike.view.activity;

import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.design.widget.BottomSheetDialog;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TextInputLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
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

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class PublishActivity extends MVPActivity<PublishPresenter> implements PublishViewImp, View.OnClickListener {


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

    public static final int ABLUM_CODE = 1001;
    public static final int CAMARE_CODE = 1002;

    private ArrayList<String> mBitmaps;
    private PhotoAdapter mAdapter;
    private RecyclerView.LayoutManager mManager;
    private BottomSheetDialog mDialog;
    private View mDialogView;
    private Button mBtnCamera, mBtnAlbum, mBtnCancel;
    private Uri imageUri;
    private File mOutputImage;

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
        mDialog = new BottomSheetDialog(this);
    }

    @Override
    public void initView() {
        setContentView(R.layout.activity_publish);
        ButterKnife.bind(this);
        mToolbar.setTitle(getString(R.string.publishdynamic));
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mRecyclerView.setLayoutManager(mManager);
        mRecyclerView.setAdapter(mAdapter);
        mDialogView = LayoutInflater.from(this).inflate(R.layout.bottom_dialog_getpicture, null);
        mDialog.setContentView(mDialogView);
        mBtnCamera = (Button) mDialogView.findViewById(R.id.btn_camera);
        mBtnAlbum = (Button) mDialogView.findViewById(R.id.btn_fromlocal);
        mBtnCancel = (Button) mDialogView.findViewById(R.id.btn_cancle);
    }

    @Override
    public void setListener() {
        mBtnCancel.setOnClickListener(this);
        mBtnCamera.setOnClickListener(this);
        mBtnAlbum.setOnClickListener(this);

    }

    @Override
    public void showSnakBar(String msg, int type) {
        SnackBarUtil.ShortSnackbar(mActivityPublish, msg, type).show();
    }


    @OnClick({R.id.fab_publish, R.id.btn_addPhoto})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.fab_publish:
                // TODO 上传动态
                mPresenter.publishInfo();
                break;
            case R.id.btn_addPhoto:
                mDialog.show();
                break;
            case R.id.btn_camera:
                mDialog.dismiss();
                if (mAdapter.getItemCount() < 9) {
                    startCamare();
                } else {
                    showSnakBar(getString(R.string.nomore), 1);
                }
                break;
            case R.id.btn_cancle:
                mDialog.dismiss();
                break;
            case R.id.btn_fromlocal:
                Intent intent = new Intent(this, SelectPictureActivity.class);
                intent.putStringArrayListExtra("alreadhave", mBitmaps);
                startActivityForResult(intent, ABLUM_CODE);
                mDialog.dismiss();
                break;
        }
    }

    private void startCamare() {
        mOutputImage = new File(getExternalCacheDir(), "output_image.jpg");
        try {
            if (mOutputImage.exists()) {
                mOutputImage.delete();
            }
            mOutputImage.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        imageUri = Uri.fromFile(mOutputImage);
        Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
        intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
        startActivityForResult(intent, CAMARE_CODE);
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
        if (item.getItemId() == R.id.action_location) {
            // TODO 获取位置
            item.setIcon(null);
            item.setTitle("北京");
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && data != null) {
            if (requestCode == ABLUM_CODE) {
                ArrayList<String> picture = data.getStringArrayListExtra("picture");
                mAdapter.flushData(picture);
            }
            if (requestCode == CAMARE_CODE) {
                mAdapter.getData().add(mOutputImage.getAbsolutePath());
                mAdapter.notifyItemInserted(mAdapter.getItemCount());
            }
        }
    }
}
