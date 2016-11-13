package com.panda.teller.views.actionbar;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

import com.panda.teller.R;

/**
 * Created by root on 16-11-10.
 */

public class MainActionbar extends LinearLayout {



    public MainActionbar(Context context) {
        super(context);
        LayoutInflater.from(context).inflate(R.layout.actionbar_main, this);
    }

    public MainActionbar(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.actionbar_main, this);
    }

}
