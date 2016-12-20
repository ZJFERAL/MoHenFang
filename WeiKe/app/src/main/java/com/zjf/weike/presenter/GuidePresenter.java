package com.zjf.weike.presenter;

import com.zjf.weike.model.GuideModel;
import com.zjf.weike.model.modelimp.FragmentModelImp;
import com.zjf.weike.presenter.base.BasePresenter;
import com.zjf.weike.view.viewimp.GuideViewImp;

/**
 * @author :ZJF
 * @version : 2016-12-16 下午 4:32
 */

public class GuidePresenter implements BasePresenter<GuideViewImp> {

    private FragmentModelImp mModel;
    private GuideViewImp mView;

    public GuidePresenter(GuideViewImp view) {
        mView = view;
        mModel = new GuideModel();
    }


    @Override
    public void onViewAttached(GuideViewImp view) {
       mView.setFragment(mModel.getFragments());
    }

    @Override
    public void onViewDeached() {

    }

    @Override
    public void onDestroyed() {

    }
}
