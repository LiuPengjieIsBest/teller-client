package com.panda.teller.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.panda.teller.R;
import com.panda.teller.models.Quiz;
import com.panda.teller.views.adapters.QuizAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by root on 16-11-20.
 */

public class QuizListFragment extends Fragment {

    View quizListLayout;

    ListView quizListView;

    List<Quiz> quizs;

    QuizAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        quizListLayout = inflater.inflate(R.layout.fragment_quizlist, container, false);
        initView();
        return quizListLayout;
    }

    private void initView() {
        quizListView = (ListView) quizListLayout.findViewById(R.id.lv_fragment_quizlist_list);
        quizs = new ArrayList<Quiz>();
        adapter = new QuizAdapter(getContext(), R.layout.quiz_item_long, quizs);
        quizListView.setAdapter(adapter);
        quizListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                /* 点击每个问题单元后跳转到一个页面显示对应问题的详细信息 */
                Quiz q = quizs.get(position);
                /*  */
            }
        });

        quizs.add(new Quiz());
        quizs.add(new Quiz());
        quizs.add(new Quiz());
        quizs.add(new Quiz());
        quizs.add(new Quiz());
        quizs.add(new Quiz());
        quizs.add(new Quiz());
        quizs.add(new Quiz());
        quizs.add(new Quiz());
    }

}
