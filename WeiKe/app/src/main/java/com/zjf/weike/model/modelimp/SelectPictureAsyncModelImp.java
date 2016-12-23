package com.zjf.weike.model.modelimp;

import com.zjf.weike.bean.ImageFolder;
import com.zjf.weike.imp.OnAsyncModelListener;

/**
 * @author :ZJF
 * @version : 2016-12-22 下午 3:34
 */

public interface SelectPictureAsyncModelImp extends BaseAsyncModelImp<ImageFolder> {
    void getPicture(String folderName,OnAsyncModelListener<String> listener);
}
