package com.panda.teller.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;

import com.panda.teller.R;
import com.panda.teller.models.Answer;
import com.panda.teller.views.adapters.AnswerAdapter;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by root on 16-11-20.
 */

public class MyAnswerFragment extends BaseFragment implements View.OnClickListener {

    /* “我的问题”页的布局 */
    private View myAnswerLayout;

    /* 问题列表视图 */
    private ListView listView;

    private AnswerAdapter adapter;

    private List<Answer> answers;

    private ImageView undoImage;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        answers = new ArrayList<Answer>();
        myAnswerLayout = inflater.inflate(R.layout.fragment_myanswer, null, false);
        initView();
        return myAnswerLayout;
    }

    /* 主要是初始化actionbar和视频列表 */
    public void initView() {
        adapter = new AnswerAdapter(getContext(), R.layout.answer_item_long, answers);
        listView = (ListView) myAnswerLayout.findViewById(R.id.lv_fragment_myanswer_list);
        listView.setAdapter(adapter);
        addItem(new Answer());
        addItem(new Answer());

        undoImage = (ImageView) myAnswerLayout.findViewById(R.id.iv_fragment_myanswer_undo);
        undoImage.setOnClickListener(this);
    }

    void addItem(Answer answer) {
        answers.add(answer);
        adapter.notifyDataSetChanged();
        listView.setSelection(answers.size());
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()) {
        case R.id.iv_fragment_myanswer_undo:
            /* 点击左上角返回按钮，隐藏当前Fragment */
            mActivity.hideFragment(this);
            break;
        default:
            break;
        }
    }
}
