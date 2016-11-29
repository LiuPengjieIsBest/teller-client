package com.panda.teller.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.panda.teller.R;

/**
 * Created by root on 16-11-8.
 */

public class FollowFragment extends Fragment {

    /* 关注界面中的三个按钮 */
    Button videoBtn;
    Button quizBtn;
    Button draftBtn;

    /* 三个按钮对应的Fragment */

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View starLayout = inflater.inflate(R.layout.fragment_follow, container, false);
        return starLayout;
    }

    private void initView() {

    }


}
