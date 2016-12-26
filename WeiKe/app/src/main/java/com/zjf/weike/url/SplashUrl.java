package com.zjf.weike.url;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.http.GET;

/**
 * @author :ZJF
 * @version : 2016-12-26 上午 9:56
 */

public interface SplashUrl {

    @GET(value = "api/bing_pic")
    Observable<ResponseBody> getBackGround();
}
