package com.zjf.weike.view.fragment;


import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zjf.weike.R;
import com.zjf.weike.adapter.CRecyclerViewAdapter;
import com.zjf.weike.adapter.GankAdapter;
import com.zjf.weike.bean.GankBean;
import com.zjf.weike.presenter.GankPresenter;
import com.zjf.weike.view.activity.MainActivity;
import com.zjf.weike.view.activity.WebViewActivity;
import com.zjf.weike.view.fragment.base.MVPFragment;
import com.zjf.weike.view.viewimp.GankViewImp;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class GankFragment extends MVPFragment<GankPresenter> implements GankViewImp {


    public static final String TYPE = "TYPE";
    public static final int TYPE_ANDROID = 0;
    public static final int TYPE_IOS = 1;
    public static final int TYPE_WEB = 2;

    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;
    @BindView(R.id.refreshLayout)
    SwipeRefreshLayout mRefreshLayout;
    @BindView(R.id.emptyView)
    TextView mEmptyView;

    private GankAdapter mAdapter;
    private int mType;
    private List<GankBean.ResultsBean> mData;

    public GankFragment() {
        // Required empty public constructor
    }

    @Override
    public void initVariables() {
        mData = new ArrayList<>();
    }

    @Override
    public View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_gank, container, false);
        ButterKnife.bind(this, view);
        mType = getArguments().getInt(GankFragment.TYPE);
        RecyclerView.LayoutManager manager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(manager);
        mAdapter = new GankAdapter(getContext(), mData, R.layout.gank_item);
        mAdapter.setEmptyView(mEmptyView);
        mRecyclerView.setAdapter(mAdapter);
        mRefreshLayout.setColorSchemeColors(Color.BLUE, Color.YELLOW);
        return view;
    }

    @Override
    public void initListener() {
        mRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mPresenter.flushData();
            }
        });
        mRecyclerView.addOnScrollListener(new CRecyclerViewAdapter.LLM_RefreshListenter() {
            @Override
            protected void loadMore() {
                mPresenter.loadMore();
            }
        });
        mRecyclerView.addOnItemTouchListener(new CRecyclerViewAdapter.RecyclerItemClickListener(getContext(), new CRecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                String url = mAdapter.getData().get(position).getUrl();
                Intent intent = new Intent(getContext(), WebViewActivity.class);
                intent.putExtra("path", url);
                startActivity(intent);
            }
        }));
    }


    @Override
    public GankPresenter create() {
        return new GankPresenter(mType);
    }

    @Override
    public void showSnakBar(String msg, int type) {
        ((MainActivity) getActivity()).showSnakBar(msg, type);
    }

    @Override
    public void loaderData() {
        super.loaderData();
    }


    @Override
    public void flushData(GankBean bean) {
        mAdapter.flushData(bean.getResults());
        if (mRefreshLayout.isRefreshing()) {
            mRefreshLayout.setRefreshing(false);
        }
    }

    @Override
    public void moreData(GankBean bean) {
        mAdapter.addNewData(bean.getResults());
        mAdapter.onCompleteLoading();
    }
}
