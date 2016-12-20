package com.zjf.weike.view.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.zjf.weike.R;
import com.zjf.weike.view.activity.MainActivity;
import com.zjf.weike.view.fragment.base.BaseFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 首次启动引导页
 */
public class GuideFragment extends BaseFragment {


    @BindView(R.id.bg)
    ImageView mBg;
    @BindView(R.id.img_top)
    ImageView mImgTop;
    @BindView(R.id.img_middle)
    ImageView mImgMiddle;
    @BindView(R.id.img_bottom)
    ImageView mImgBottom;
    @BindView(R.id.btn_guide)
    Button mBtnGuide;

    private int page;

    public GuideFragment() {
        // Required empty public constructor
    }


    public void setPage(int page) {
        this.page = page;
    }


    @Override
    public void initVariables() {

    }

    @Override
    public View initView(LayoutInflater inflater, ViewGroup container,
                         Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_guide, container, false);
        ButterKnife.bind(this, view);
        return view;

    }

    @Override
    public void initListener() {
        mBtnGuide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), MainActivity.class));
                getActivity().finish();
            }
        });
    }

    @Override
    public void loaderData() {
        switch (page) {
            case 1:
                Glide.with(this)
                        .load(R.drawable.guide_bg1)
                        .into(mBg);
                break;
            case 2:
                mImgTop.setVisibility(View.INVISIBLE);
                mImgBottom.setVisibility(View.VISIBLE);
                Glide.with(this)
                        .load(R.drawable.guide_bg2)
                        .into(mBg);
                break;
            case 3:
                mImgTop.setVisibility(View.INVISIBLE);
                mImgMiddle.setVisibility(View.VISIBLE);
                mBtnGuide.setVisibility(View.VISIBLE);
                Glide.with(this)
                        .load(R.drawable.guide_bg3)
                        .into(mBg);
                break;
        }
    }

}
