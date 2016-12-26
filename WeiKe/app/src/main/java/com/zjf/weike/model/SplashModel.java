package com.zjf.weike.model;

import com.zjf.weike.impl.OnAsyncModelListener;
import com.zjf.weike.model.modelimp.SplashModelImp;
import com.zjf.weike.url.SplashUrl;
import com.zjf.weike.util.RetrofitUtil;

import java.io.IOException;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;
import retrofit2.Retrofit;

/**
 * @author :ZJF
 * @version : 2016-12-24 上午 10:50
 */

public class SplashModel implements SplashModelImp {

    @Override
    public void getData(final OnAsyncModelListener<String> listener) {
        listener.onSuccess("1.00");
    }

    @Override
    public void getBackGround(final OnAsyncModelListener<String> listener) {
        Retrofit client = RetrofitUtil.getClient("http://guolin.tech/");
        SplashUrl url = client.create(SplashUrl.class);
        url.getBackGround().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ResponseBody>() {
                    @Override
                    public void accept(ResponseBody body) throws Exception {
                        try {
                            listener.onSuccess(body.string());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                });
    }
}
