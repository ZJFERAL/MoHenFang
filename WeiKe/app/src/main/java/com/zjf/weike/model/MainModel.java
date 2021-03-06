package com.zjf.weike.model;

import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.Fragment;

import com.zjf.weike.App;
import com.zjf.weike.R;
import com.zjf.weike.impl.OnAsyncModelListener;
import com.zjf.weike.model.modelimp.MainModelImp;
import com.zjf.weike.url.ApiService;
import com.zjf.weike.util.RetrofitUtil;
import com.zjf.weike.util.SC;
import com.zjf.weike.util.SDCardUtil;
import com.zjf.weike.view.fragment.GankFragment;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;
import retrofit2.Retrofit;

/**
 * @author :ZJF
 * @version : 2016-12-27 上午 9:50
 */

public class MainModel implements MainModelImp {

    private String url;
    private List<Fragment> mFragments;


    public MainModel() {
        mFragments = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            GankFragment fragment = new GankFragment();
            Bundle bundle = new Bundle();
            bundle.putInt(GankFragment.TYPE, i);
            fragment.setArguments(bundle);
            mFragments.add(fragment);
        }
    }

    @Override
    public void setUrl(String url) {
        this.url = url.substring(url.indexOf("com") + 4);
    }

    @Override
    public GankFragment getTargetFragment(int target) {
        return (GankFragment) mFragments.get(target);
    }

    @Override
    public void getData(final OnAsyncModelListener<String> listener) {
        Retrofit client = RetrofitUtil.getClient(SC.BING_BASE);
        ApiService service = client.create(ApiService.class);
        service.downloadFile(url)
                .observeOn(Schedulers.io())
                .subscribeOn(Schedulers.io())
                .subscribe(new Consumer<ResponseBody>() {
                    @Override
                    public void accept(ResponseBody body) throws Exception {
                        String filename = url.substring(url.lastIndexOf("/") + 1);
                        boolean success = SDCardUtil.saveByteArrayFileToExternalStoragePublicDir(body.bytes(),
                                Environment.DIRECTORY_DOWNLOADS, filename);
                        if (success) {
                            listener.onSuccess(App.getStringRes(R.string.downsuccess));
                        } else {
                            listener.onFailure(App.getStringRes(R.string.downfailure), 3);
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        listener.onFailure(App.getStringRes(R.string.downfailure), 3);
                    }
                });
    }



}
