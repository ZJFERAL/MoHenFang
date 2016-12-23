package com.zjf.weike.adapter;

import android.content.Context;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.zjf.weike.R;
import com.zjf.weike.imp.OnPictureSelectListener;
import com.zjf.weike.util.LogUtil;
import com.zjf.weike.view.activity.SelectPictureActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * @author :ZJF
 * @version : 2016-12-22 下午 4:59
 */

public class SelectPictureAdapter extends CRecyclerViewAdapter<String> {

    private ArrayList<String> selectList;
    private OnPictureSelectListener mListener;
    private float width;
    private int mNum;

    public void setNum(int num) {
        mNum = num;
    }

    public void setListener(OnPictureSelectListener listener) {
        mListener = listener;
    }

    public ArrayList<String> getSelectList() {
        return selectList;
    }

    public SelectPictureAdapter(Context context, List<String> data, int... itemLayoutIds) {
        super(context, data, itemLayoutIds);
        selectList = new ArrayList<>();
        width = context.getResources().getDisplayMetrics().widthPixels / 3.0f;
    }


    @Override
    protected void setConvertView(CRecyclerViewViewHolder holder, final String item, int position) {
        CheckBox view = holder.getView(R.id.item_check);
        if (selectList.contains(item)) {
            view.setChecked(true);
        } else {
            view.setChecked(false);
        }
        LogUtil.e("checkbox", view.isChecked() + "  " + position);
        Glide.with(mContext)
                .load(item)
                .override((int) width, (int) width)
                .into(((ImageView) holder.getView(R.id.item_photo)));

        view.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    if (selectList.size() < mNum) {
                        selectList.add(item);
                    } else {
                        buttonView.setChecked(false);
                        ((SelectPictureActivity) mContext).showSnakBar(mContext.getString(R.string.ninepicture), 1);
                    }
                }
                LogUtil.e("checkbox", isChecked + "");
                if (!isChecked && selectList.size() > 0) {
                    selectList.remove(item);
                }
                if (mListener != null) {
                    mListener.pictureSelect(selectList.size());
                }
            }
        });
    }
}
