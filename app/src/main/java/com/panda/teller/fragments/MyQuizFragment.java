package com.panda.teller.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;

import com.panda.teller.R;
import com.panda.teller.models.Answer;
import com.panda.teller.models.Quiz;
import com.panda.teller.views.adapters.QuizAdapter;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by root on 16-11-20.
 */

public class MyQuizFragment extends BaseFragment implements View.OnClickListener {

    /* “我的问题”页的布局 */
    private View myQuizLayout;

    /* 问题列表视图 */
    private ListView listView;

    private QuizAdapter adapter;

    private List<Quiz> Quizes;

    private ImageView undoImage;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        Quizes = new ArrayList<Quiz>();
        myQuizLayout = inflater.inflate(R.layout.fragment_myquiz, null, false);
        initView();
        return myQuizLayout;
    }

    /* 主要是初始化actionbar和问题列表 */
    public void initView() {
        adapter = new QuizAdapter(getContext(), R.layout.answer_item, Quizes);
        listView = (ListView) myQuizLayout.findViewById(R.id.lv_fragment_myanswer_list);
        listView.setAdapter(adapter);
        addItem(new Quiz());
        addItem(new Quiz());

        undoImage = (ImageView) myQuizLayout.findViewById(R.id.iv_fragment_myanswer_undo);
        undoImage.setOnClickListener(this);
    }

    void addItem(Quiz quiz) {
        Quizes.add(quiz);
        adapter.notifyDataSetChanged();
        listView.setSelection(Quizes.size());
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
