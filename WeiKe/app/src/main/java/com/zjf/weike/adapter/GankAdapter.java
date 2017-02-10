package com.zjf.weike.adapter;

import android.content.Context;

import com.zjf.weike.R;
import com.zjf.weike.bean.GankBean;

import java.util.List;

/**
 * @author :ZJF
 * @version : 2016-12-18 上午 8:34
 */

public class GankAdapter extends CRecyclerViewAdapter<GankBean.ResultsBean> {

    public GankAdapter(Context context, List<GankBean.ResultsBean> data, int... itemLayoutIds) {
        super(context, data, itemLayoutIds);
    }

    @Override
    protected void setConvertView(CRecyclerViewViewHolder holder, GankBean.ResultsBean item, int position) {
        String time = item.getCreatedAt();
        holder.setText(R.id.item_time, "  " + time.substring(0, time.indexOf("T")))
                .setText(R.id.item_subject, item.getDesc())
                .setText(R.id.item_author, "  Author：" + item.getWho());
        if (item.getImages() != null && item.getImages().size() > 0) {
            holder.setImageByUrl(R.id.item_example, item.getImages().get(0));
        }
    }
}
