package com.panda.teller.fragments;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.panda.teller.R;

/**
 * Created by root on 16-11-8.
 */

public class SubmitFragment extends BaseFragment implements View.OnClickListener {

    /* 整个界面对象 */
    View submitLayout;

    /* 提交界面中央的三个按钮 */
    Button submitQuizBtn;
    Button submitVideoBtn;
    Button submitDraftBtn;

    /* 点击三个按钮后将显示的界面 */
    SubmitQuizFragment submitQuizFragment;
    SubmitVideoFragment submitVideoFragment;
    SubmitDraftFragment submitDraftFragment;

    FragmentManager fragmentManager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        submitLayout = inflater.inflate(R.layout.fragment_submit, container, false);
        fragmentManager = getFragmentManager();
        initView();
        return submitLayout;
    }

    private void initView() {
        submitQuizFragment = new SubmitQuizFragment();
        submitVideoFragment = new SubmitVideoFragment();
        submitDraftFragment = new SubmitDraftFragment();
        submitQuizBtn = (Button) submitLayout.findViewById(R.id.btn_fragment_submit_quiz);
        submitVideoBtn = (Button) submitLayout.findViewById(R.id.btn_fragment_submit_video);
        submitDraftBtn = (Button) submitLayout.findViewById(R.id.btn_fragment_submit_draft);
        submitQuizBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()) {
        case R.id.btn_fragment_submit_quiz:
            mActivity.showFragment(submitQuizFragment);
            break;
        case R.id.btn_fragment_submit_video:
            mActivity.showFragment(submitVideoFragment);
            break;
        case R.id.btn_fragment_submit_draft:
            mActivity.showFragment(submitDraftFragment);
            break;
        default:
            break;
        }
    }
}
