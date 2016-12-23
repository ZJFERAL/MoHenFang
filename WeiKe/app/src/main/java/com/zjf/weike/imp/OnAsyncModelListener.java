package com.zjf.weike.imp;

import java.util.List;

/**
 * @author :ZJF
 * @version : 2016-12-22 下午 3:23
 */

public interface OnAsyncModelListener<T> {

    void onFailure(String msg,int type);

    void onSuccess(List<T> list);

}
