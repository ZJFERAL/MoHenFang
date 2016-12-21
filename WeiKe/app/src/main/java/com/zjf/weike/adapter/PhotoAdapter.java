package com.zjf.weike.adapter;

import android.content.Context;
import android.graphics.Bitmap;

import com.zjf.weike.R;

import java.util.List;

/**
 * @author :ZJF
 * @version : 2016-12-21 下午 8:04
 */

public class PhotoAdapter extends CRecyclerViewAdapter<Bitmap> {

    public PhotoAdapter(Context context, List<Bitmap> data, int... itemLayoutIds) {
        super(context, data, itemLayoutIds);
    }

    @Override
    protected void setConvertView(CRecyclerViewViewHolder holder, Bitmap item, int position) {
        holder.setImageBitmap(R.id.item_photo, item);
    }
}
