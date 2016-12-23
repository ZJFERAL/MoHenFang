package com.zjf.weike.imp;

import java.util.List;

/**
 * @author :ZJF
 * @version : 2016-12-22 下午 3:26
 */

public interface OnAsyncModel2SListener<T,V> extends OnAsyncModelListener<T> {
    void onAction(List<V> list);
}
