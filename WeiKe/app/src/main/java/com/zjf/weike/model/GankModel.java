package com.zjf.weike.model;

import com.zjf.weike.App;
import com.zjf.weike.R;
import com.zjf.weike.bean.GankBean;
import com.zjf.weike.impl.OnAsyncModelListener;
import com.zjf.weike.model.modelimp.GankModelImp;
import com.zjf.weike.url.ApiService;
import com.zjf.weike.util.RetrofitUtil;
import com.zjf.weike.util.SC;
import com.zjf.weike.view.fragment.GankFragment;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

/**
 * @author :ZJF
 * @version : 2016-12-21 下午 9:39
 */

public class GankModel implements GankModelImp {

    private String baseUrl = "";
    private ApiService mService;

    @Override
    public void flushData(final OnAsyncModelListener<GankBean> listener, int type) {
        if(mService==null){
            String baseUrl = getURl(type);
            Retrofit client = RetrofitUtil.getClient(baseUrl);
            mService = client.create(ApiService.class);
        }
        mService.getData(baseUrl + 1)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<GankBean>() {
                    @Override
                    public void accept(GankBean bean) throws Exception {
                        listener.onSuccess(bean);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        listener.onFailure(App.getStringRes(R.string.failure), 3);
                    }
                });

    }

    private String getURl(int type) {
        baseUrl = SC.GANKIO_BASE;
        switch (type) {
            case GankFragment.TYPE_ANDROID:
                baseUrl += "Android/20/";
                break;
            case GankFragment.TYPE_IOS:
                baseUrl += "iOS/20/";
                break;
            case GankFragment.TYPE_WEB:
                String qd = "";
                try {
                    qd = URLEncoder.encode("前端", "utf-8");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                baseUrl += qd+"/20/";
                break;
        }
        return baseUrl;
    }

    @Override
    public void getMoreData(final OnAsyncModelListener<GankBean> listener, int type, int page) {
        mService.getData(baseUrl + page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<GankBean>() {
                    @Override
                    public void accept(GankBean bean) throws Exception {
                       listener.onSuccess(bean);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        listener.onFailure(App.getStringRes(R.string.failure), 3);
                    }
                });
    }
}
