package com.zjf.weike.adapter;

import android.content.Context;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import com.zjf.weike.R;
import com.zjf.weike.imp.OnPictureSelectListener;

import java.util.ArrayList;
import java.util.List;

/**
 * @author :ZJF
 * @version : 2016-12-22 下午 4:59
 */

public class SelectPictureAdapter extends CRecyclerViewAdapter<String> {

    private ArrayList<String> selectList;
    private OnPictureSelectListener mListener;

    public void setListener(OnPictureSelectListener listener) {
        mListener = listener;
    }

    public ArrayList<String> getSelectList() {
        return selectList;
    }

    public SelectPictureAdapter(Context context, List<String> data, int... itemLayoutIds) {
        super(context, data, itemLayoutIds);
        selectList = new ArrayList<>();
    }

    @Override
    protected void setConvertView(CRecyclerViewViewHolder holder, final String item, int position) {
        holder.setImageByUrl(R.id.item_photo, item);
        ((CheckBox) holder.getView(R.id.item_check)).setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked && selectList.size() <= 9) {
                    selectList.add(item);
                } else {
                    selectList.remove(item);
                }
                if (mListener != null) {
                    mListener.pictureSelect(selectList.size());
                }
            }
        });
    }
}
