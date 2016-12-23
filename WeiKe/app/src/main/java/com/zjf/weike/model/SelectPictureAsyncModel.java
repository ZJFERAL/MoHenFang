package com.zjf.weike.model;

import android.os.Environment;

import com.zjf.weike.bean.ImageFolder;
import com.zjf.weike.imp.OnAsyncModel2SListener;
import com.zjf.weike.imp.OnAsyncModelListener;
import com.zjf.weike.model.modelimp.SelectPictureAsyncModelImp;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * @author :ZJF
 * @version : 2016-12-22 下午 3:28
 */

public class SelectPictureAsyncModel implements SelectPictureAsyncModelImp {

    private List<String> mPictures;
    private List<ImageFolder> mFolder;

    public SelectPictureAsyncModel() {
        mPictures = new ArrayList<>();
        mFolder = new ArrayList<>();
    }


    /**
     * 根据文件夹名称获取图片
     *
     * @param folderName
     * @param listener
     */
    @Override
    public void getPicture(String folderName, OnAsyncModelListener<String> listener) {

    }

    /**
     * 第一次获取
     *
     * @param listener
     */
    @Override
    public void getData(final OnAsyncModel2SListener<ImageFolder, String> listener) {
        if (!Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED))
        {
            listener.onFailure();
            return;
        }
        Observable.create(new ObservableOnSubscribe<List<ImageFolder>>() {
            @Override
            public void subscribe(ObservableEmitter<List<ImageFolder>> e) throws Exception {

            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<ImageFolder>>() {
                    @Override
                    public void accept(List<ImageFolder> folder) throws Exception {

                    }
                });
    }

}
