package com.zjf.weike.presenter;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;

import com.zjf.weike.R;
import com.zjf.weike.presenter.base.BasePresenter;
import com.zjf.weike.view.activity.SelectPictureActivity;
import com.zjf.weike.view.viewimp.PublishViewImp;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import static com.zjf.weike.view.activity.PublishActivity.ABLUM_CODE;

/**
 * @author :ZJF
 * @version : 2016-12-21 下午 5:02
 */

public class PublishPresenter implements BasePresenter {

    private PublishViewImp mView;

    public PublishPresenter(PublishViewImp view) {
        mView = view;
    }

    public void publishInfo() {
        // 上传数据
        mView.published();
    }

    public void getAblumPicture(Activity activity, ArrayList<String> mBitmaps) {
        Intent intent = new Intent(activity, SelectPictureActivity.class);
        intent.putStringArrayListExtra("alreadhave", mBitmaps);
        activity.startActivityForResult(intent, ABLUM_CODE);
        mView.dimissBottomSheetDialog();
    }

    public void getLoaction() {

    }

    public File startCamera(Activity activity, int count,int CAMERA_CODE) {
        mView.dimissBottomSheetDialog();
        if (count < 9) {
            File outputImage = new File(activity.getExternalCacheDir(), "output_image.jpg");
            try {
                if (outputImage.exists()) {
                    outputImage.delete();
                }
                outputImage.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Uri imageUri = Uri.fromFile(outputImage);
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
            activity.startActivityForResult(intent, CAMERA_CODE);
            return outputImage;
        } else {
            mView.showSnakBar(activity.getString(R.string.nomore), 1);
            return null;
        }
    }

    @Override
    public void onViewAttached(Object view) {

    }

    @Override
    public void onViewDeached() {

    }

    @Override
    public void onDestroyed() {

    }
}
