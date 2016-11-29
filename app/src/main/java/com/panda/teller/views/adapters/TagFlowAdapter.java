package com.panda.teller.views.adapters;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.panda.teller.R;
import com.panda.teller.models.Tag;
import com.panda.teller.utils.StringUtil;
import com.panda.teller.views.widgets.FlowLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by root on 16-11-25.
 * 用于显示标签布局
 * 因为各标签长度不同，且可以动态添加，所以使用流式布局实现
 * 该Adapter的作用是向FlowLayout中动态添加Tag，具体为将Tag的属性解析到TextView
 * 且最后一位将显示一个加号图片，点击后将弹出AlertDialog提示用户输入一个Tag
 */

public class TagFlowAdapter implements View.OnClickListener {

    private FlowLayout flowLayout;

    /* 流式布局所处的父布局 */
    private Context context;

    /* 所有子视图 */
    private List<TextView> views;

    /* 所有子视图对应的tag */
    private List<Tag> tags;

    /* 该布局包括一个加号 */
    private View appendTag;

    public TagFlowAdapter(Context context, FlowLayout flowLayout) {
        this.context = context;
        views = new ArrayList<TextView>();
        tags = new ArrayList<Tag>();
        this.flowLayout = flowLayout;
        initView();
    }

    private void initView() {
        /* 使用append_tag_img.xml，向布局末尾添加一个“加号” */
        LinearLayout container = (LinearLayout) LayoutInflater
                .from(context)
                .inflate(R.layout.append_tag_img, null);
        appendTag = container.findViewById(R.id.rl_append_tag_img);
        container.removeView(appendTag);
        flowLayout.addView(appendTag);
        appendTag.setOnClickListener(this);
        flowLayout.requestLayout();
    }

    void resetAppendTag() {
        flowLayout.removeView(appendTag);
        flowLayout.addView(appendTag);
        flowLayout.requestLayout();
    }

    /**
     * 总是在最后一项之前加入
     * @param tag 希望显示的标签
     */
    public void addItem(Tag tag) {
        /* 使用TextFlowAdapter中用过的text布局显示 */
        LinearLayout container = (LinearLayout) LayoutInflater
                .from(context)
                .inflate(R.layout.textflow_item, null);
        TextView child = (TextView) container.findViewById(R.id.tv_textflow_item_txt);
        views.add(child);
        tags.add(tag);
        /* 使用标签的内容来填充每一个标签 */
        child.setText(tag.getName());
        container.removeView(child);
        flowLayout.addView(child);
        /* 设置监听器 */
        child.setOnClickListener(this);
        /* 更新视图 */
        flowLayout.requestLayout();
    }

    /**
     * 删除标签
     * @param pos 标签位置
     */
    public void removeItem(int pos) {
        flowLayout.removeView(views.get(pos));
        tags.remove(pos);
        views.remove(pos);
        /* 更新界面 */
        flowLayout.requestLayout();
    }

    /**
     * 更新标签
     * @param pos 该标签位置
     */
    public void updateItem(int pos, String newTagStr) {
        tags.get(pos).setName(newTagStr);
        views.get(pos).setText(newTagStr);
        flowLayout.requestLayout();
    }

    /**
     * 根据标签内容得到标签位置
     * @param tagStr 标签内容
     * @return 标签位置
     */
    public int getPos(String tagStr) {
        /* 获取该tag位置 */
        int cur;
        for(cur = 0; cur < tags.size() && !StringUtil.isEqual(tags.get(cur).getName(), tagStr); cur++) {
        }
        return cur;
    }

    @Override
    public void onClick(final View v) {
        /* 弹出框的内部视图 */
        final View content;
        final EditText edt;
        switch(v.getId()) {
        case R.id.rl_append_tag_img:
            /* 显示标签编辑框 */
            content = LayoutInflater
                    .from(context)
                    .inflate(R.layout.edttxt_tag, null);
            edt = (EditText) content.findViewById(R.id.edtTxt_edttxt_tag);
            new AlertDialog.Builder(context)
                    .setTitle("请输入标签")
                    .setView(content) /* 设置为编辑框布局 */
                    .setCancelable(false)
                    .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            /* 创建一个新标签 */
                            String tagStr = edt.getText().toString();
                            Tag tag = new Tag();
                            tag.setName(tagStr);
                            tag.setLevel(2);
                            tag.setRemove(false);
                            /* 添加到流式布局中 */
                            addItem(tag);
                            resetAppendTag();
                        }
                    })
                    .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            /* 什么都不做 */
                        }
                    })
                    .create().show();
            break;
        case R.id.tv_textflow_item_txt:
            /* 点击已添加的一项后显示对话框，询问是否删除 */
            content = LayoutInflater
                    .from(context)
                    .inflate(R.layout.edttxt_tag, null);
            edt = (EditText) content.findViewById(R.id.edtTxt_edttxt_tag);
            /* 设置已输入的内容 */
            String tagStr = ((TextView)v).getText().toString();
            edt.setText(tagStr);
            edt.setSelection(tagStr.length());
            new AlertDialog.Builder(context)
                    .setTitle("编辑标签")
                    .setView(content)
                    .setCancelable(false)
                    .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            /* 根据标签内容获取标签位置 */
                            int pos = getPos(((TextView)v).getText().toString());
                            /* 使用编辑框中的内容填充tag */
                            String newTagStr = edt.getText().toString();
                            if(StringUtil.isEmpty(newTagStr)) {
                                /* 若为空则删除 */
                                removeItem(pos);
                                Log.e("TagFlowAdapter", "removed");
                            } else {
                                /* 否则更新该标签 */
                                updateItem(pos, newTagStr);
                            }
                        }
                    })
                    .setNeutralButton("删除", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            /* 根据标签内容获取标签位置 */
                            int pos = getPos(((TextView)v).getText().toString());
                            /* 删除该标签 */
                            removeItem(pos);
                        }
                    })
                    .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            /* 什么都不做 */
                        }
                    })
                    .create().show();
            break;
        default:
            break;
        }
    }
}
