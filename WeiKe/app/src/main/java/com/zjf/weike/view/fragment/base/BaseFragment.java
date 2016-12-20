package com.zjf.weike.view.fragment.base;


import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zjf.weike.imp.JumpInto;
import com.zjf.weike.imp.JumpTo;

/**
 * A simple {@link Fragment} subclass.
 */
public abstract class BaseFragment extends Fragment {

    private JumpTo mTo;

    public BaseFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mTo = new JumpInto();
        initVariables();
        View view = initView(inflater, container, savedInstanceState);
        initListener();
        return view;
    }

    public abstract void initVariables();

    public abstract View initView(LayoutInflater inflater, ViewGroup container,
                                  Bundle savedInstanceState);

    public abstract void initListener();

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        loaderData();
    }

    public abstract void loaderData();

    public void jumpTo(final Activity activity, final Class<? extends Activity> aClazz, int delay) {
        mTo.jumpTo(activity, aClazz, delay);
    }

    public void jumpTo(final Activity activity, final Class<? extends Activity> aClazz, int delay, final Bundle bundle) {
        mTo.jumpTo(activity, aClazz, delay, bundle);
    }


    public void jumpTo(Activity activity, Class<? extends Activity> aClazz, Bundle bundle) {
        mTo.jumpTo(activity, aClazz, bundle);
    }

    public void jumpTo(Activity activity, Class<? extends Activity> aClazz) {
        mTo.jumpTo(activity, aClazz);
    }
}
