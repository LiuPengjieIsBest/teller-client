package com.panda.teller.views.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.panda.teller.models.Answer;

import java.util.List;

/**
 * Created by root on 16-11-20.
 */

public class AnswerAdapter extends ArrayAdapter<Answer> {

    private int resourceId;

    public AnswerAdapter(Context context, int answerItemLayoutResourceId, List<Answer> objects) {
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

    /* 从该view中获取控件，然后取answer的属性填充进去 */
    private void updateView(View answerItemView, Answer answer) {

    }
}
