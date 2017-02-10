package com.zjf.weike.model.modelimp;

import com.zjf.weike.bean.GankBean;
import com.zjf.weike.impl.OnAsyncModelListener;

/**
 * @author :ZJF
 * @version : 2016-12-21 下午 9:37
 */

public interface GankModelImp{

    void flushData(OnAsyncModelListener<GankBean> listener,int type);

    void getMoreData(OnAsyncModelListener<GankBean> listener,int type,int page);

}
