package com.zjf.weike.adapter;

import android.content.Context;

import com.zjf.weike.R;

import java.util.List;

/**
 * @author :ZJF
 * @version : 2016-12-21 下午 8:04
 */

public class PhotoAdapter extends CRecyclerViewAdapter<String> {

    public PhotoAdapter(Context context, List<String> data, int... itemLayoutIds) {
        super(context, data, itemLayoutIds);
    }

    @Override
    protected void setConvertView(CRecyclerViewViewHolder holder, String item, int position) {
        holder.setImageByUrl(R.id.item_photo, item);
    }
}
