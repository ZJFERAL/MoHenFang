package com.zjf.weike.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.zjf.weike.R;

import java.util.List;

/**
 * @author :ZJF
 * @version : 2016-12-21 下午 8:04
 */

public class PhotoAdapter extends CRecyclerViewAdapter<String> {

    private float width;

    public PhotoAdapter(Context context, List<String> data, int... itemLayoutIds) {
        super(context, data, itemLayoutIds);
        width = context.getResources().getDisplayMetrics().widthPixels / 3.0f;
    }

    @Override
    protected void setConvertView(final CRecyclerViewViewHolder holder, String item, int position) {
        Glide.with(mContext)
                .load(item)
                .override((int) width, (int) width)
                .into(((ImageView) holder.getView(R.id.item_photo)));
        holder.setOnclickListener(R.id.item_delete, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                removeItemData(holder.getAdapterPosition());
            }
        });
    }
}
