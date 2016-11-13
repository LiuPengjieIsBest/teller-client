package com.panda.teller.views.actionbar;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

import com.panda.teller.R;

/**
 * Created by root on 16-11-11.
 */

public class SearchActionbar extends LinearLayout {

    public SearchActionbar(Context context) {
        super(context);
        LayoutInflater.from(context).inflate(R.layout.actionbar_search, this);
    }

    public SearchActionbar(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.actionbar_search, this);
    }

}
