package com.zjf.weike.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.List;

/**
 * @author :ZJF
 * @version : 2016-12-02 上午 11:10
 */

public abstract class CRecyclerViewAdapter<T> extends RecyclerView.Adapter<CRecyclerViewViewHolder> {

    protected List<T> mData;
    protected Context mContext;
    protected int[] mItemLayoutIds;


    public CRecyclerViewAdapter(Context context, List<T> data, int... itemLayoutIds) {
        this.mContext = context;
        this.mData = data;
        this.mItemLayoutIds = itemLayoutIds;
    }



    public List<T> getData() {
        return mData;
    }

    @Override
    public CRecyclerViewViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        CRecyclerViewViewHolder holder = CRecyclerViewViewHolder.createViewHolder(mContext, parent, mItemLayoutIds[viewType], viewType);
        return holder;
    }



    @Override
    public void onBindViewHolder(CRecyclerViewViewHolder holder, int position) {
        setConvertView(holder, mData.get(position), holder.getAdapterPosition());
    }

    protected abstract void setConvertView(CRecyclerViewViewHolder holder, T item, int position);


    @Override
    public int getItemCount() {
        int count = mData != null ? mData.size() : 0;

        return count;
    }


    /**
     * 添加单条数据
     *
     * @param position
     * @param t
     */
    public void addItemData(int position, T t) {
        mData.add(position, t);
        notifyItemInserted(position);
    }

    /**
     * 添加单条数据
     *
     * @param t
     */
    public void addItemData(T t) {
        int size = mData.size();
        mData.add(size, t);
        notifyItemInserted(size);
    }

    /**
     * 移除单条数据
     *
     * @param position
     */
    public void removeItemData(int position) {
        mData.remove(position);
        notifyItemRemoved(position);
    }

    /**
     * 修改单条数据
     *
     * @param position
     * @param t
     */
    public void changItemData(int position, T t) {
        mData.set(position, t);
        notifyItemChanged(position);
    }

    /**
     * 刷新数据
     *
     * @param newData
     */
    public void flushData(List<T> newData) {
        mData.clear();
        this.addNewData(newData);
    }

    /**
     * 添加新数据集合
     *
     * @param newData
     */
    public void addNewData(List<T> newData) {
        mData.addAll(newData);
        notifyDataSetChanged();
    }

}
