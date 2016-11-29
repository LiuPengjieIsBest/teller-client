package com.panda.teller.views.widgets;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;

import com.panda.teller.R;
import com.panda.teller.models.Tag;

/**
 * Created by root on 16-11-20.
 */

public class CatgItemLayout extends RelativeLayout {

    /* R.layout.tag_item */
    View container;

    /* 一个分类就是一个一级标签 */
    Tag tag;


    public CatgItemLayout(Context context) {
        super(context);
        container = LayoutInflater.from(context).inflate(R.layout.catg_item, this);
        initView();
    }
    public CatgItemLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        container = LayoutInflater.from(context).inflate(R.layout.catg_item, this);
        initView();
    }
    /* 使用tag填充布局 */
    private void initView() {

    }

    public void setCatgItem(Tag tag) {
        this.tag = tag;
    }

}