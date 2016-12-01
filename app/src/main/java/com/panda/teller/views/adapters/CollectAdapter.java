package com.panda.teller.views.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.panda.teller.models.Collect;
import com.panda.teller.models.Video;

import java.util.List;

/**
 * Created by 51275 on 2016/12/1.
 */

public class CollectAdapter extends ArrayAdapter<Collect> {

    private int resourceId;

    public CollectAdapter(Context context, int answerItemLayoutResourceId, List<Collect> objects) {
        super(context, answerItemLayoutResourceId, objects);
        resourceId = answerItemLayoutResourceId;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(null == convertView) {
            convertView = LayoutInflater
                    .from(getContext())
                    .inflate(resourceId, null);
            updateView(convertView, getItem(position));
        } else {
            updateView(convertView, getItem(position));
        }
        return convertView;
    }

    /* 从该view中获取控件，然后取video的属性填充进去 */
    private void updateView(View collectItemView, Collect collect) {

    }
}
