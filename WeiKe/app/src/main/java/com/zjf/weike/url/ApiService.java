package com.zjf.weike.url;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.Streaming;
import retrofit2.http.Url;

/**
 * @author :ZJF
 * @version : 2016-12-26 上午 9:56
 */

public interface ApiService {

    @GET(value = "api/bing_pic")
    Observable<ResponseBody> getBackGround();

    @Streaming
    @GET
    Observable<ResponseBody> downloadFile(@Url String url);
}
