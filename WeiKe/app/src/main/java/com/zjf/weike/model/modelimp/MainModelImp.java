package com.zjf.weike.model.modelimp;

import com.zjf.weike.view.fragment.GankFragment;

/**
 * @author :ZJF
 * @version : 2016-12-27 上午 9:49
 */

public interface MainModelImp extends BaseAsyncModelImp<String>{
    void setUrl(String url);

    GankFragment getTargetFragment(int target);
}
