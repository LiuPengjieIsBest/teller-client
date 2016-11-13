package com.panda.teller.views.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.panda.teller.R;
import com.panda.teller.views.widgets.FlowLayout;

import java.util.ArrayList;
import java.util.List;



public class TextFlowAdapter {

    /* 该流式布局所处的父视图 */
    Context context;

    /* 所有子视图 */
    List<TextView> views;

    /* 每个子视图的监听器 */
    View.OnClickListener listener;

    FlowLayout flowLayout;

    public TextFlowAdapter(Context context, FlowLayout flowLayout) {
        this.context = context;
        views = new ArrayList<TextView>();
        this.flowLayout = flowLayout;
    }

    /**
     * 接收字符串并将其转换为一个TextView，显示到对应的流式布局中
     */
    public void addItem(final String text) {
        LinearLayout container = (LinearLayout) LayoutInflater.from(context).
                inflate(R.layout.textflow_item, null);
        TextView child = (TextView) container.findViewById(R.id.tv_flowlayout_item_txt);
        child.setText(text);
        container.removeView(child);
        flowLayout.addView(child);
        /* 设置监听器 */
        child.setOnClickListener(listener);
        /* 更新视图 */
        flowLayout.requestLayout();
    }

    /**
     *  设置每个子视图的监听器，必须在添加子视图之前调用
     */
    public void setOnItemClickListener(View.OnClickListener listener) {
        this.listener = listener;
    }

}
