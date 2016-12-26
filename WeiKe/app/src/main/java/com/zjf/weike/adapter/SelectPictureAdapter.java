package com.zjf.weike.adapter;

import android.content.Context;
import android.view.View;
import android.widget.CheckBox;

import com.zjf.weike.R;
import com.zjf.weike.impl.OnPictureSelectListener;
import com.zjf.weike.view.activity.SelectPictureActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * @author :ZJF
 * @version : 2016-12-22 下午 4:59
 */

public class SelectPictureAdapter extends CRecyclerViewAdapter<String> {

    private OnPictureSelectListener mListener;
    private ArrayList<String> mAlreadhave;
    private float width;


    public void setListener(OnPictureSelectListener listener) {
        mListener = listener;
    }

    public ArrayList<String> getAlreadhave() {
        return mAlreadhave;
    }

    public void setAlreadhave(ArrayList<String> alreadhave) {
        mAlreadhave = alreadhave;
    }

    public SelectPictureAdapter(Context context, List<String> data, int... itemLayoutIds) {
        super(context, data, itemLayoutIds);
        width = context.getResources().getDisplayMetrics().widthPixels / 3.0f;
    }


    @Override
    protected void setConvertView(CRecyclerViewViewHolder holder, final String item, int position) {
        CheckBox view = holder.getView(R.id.item_check);
        if (mAlreadhave.contains(item)) {
            view.setChecked(true);
        } else {
            view.setChecked(false);
        }
        holder.setImageByUrl(R.id.item_photo, item, (int) width, (int) width);
        holder.setOnclickListener(R.id.item_check, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CheckBox box = (CheckBox) v;
                boolean checked = box.isChecked();
                //点击监听时，checked与点之前反转了一次
                if (!checked) {//原来是选中
                    mAlreadhave.remove(item);
                    box.setChecked(false);
                } else {//原来是未选中
                    if (mAlreadhave.size() < 9) {//还可以选
                        mAlreadhave.add(item);
                        box.setChecked(true);
                    } else {
                        box.setChecked(false);
                        ((SelectPictureActivity) mContext).showSnakBar(mContext.getString(R.string.ninepicture), 1);
                    }
                }
                if (mListener != null) {
                    mListener.pictureSelect(mAlreadhave.size());
                }
            }
        });
    }
}
