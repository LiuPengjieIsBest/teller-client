package com.panda.teller.fragments;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.panda.teller.R;


/**
 * Created by huang on 16-11-9.
 * 用户菜单，就是左边拉出来的那个
 */

public class UserMenuFragment extends BaseFragment implements View.OnClickListener {

    private View userMenuLayout;

    /* 控制视图的显示 */
    FragmentManager fragmentManager;

    /* 点击后退出 */
    Button undo;

    /* */
    MyAnswerFragment myAnswerFragment;
    MyQuizFragment myQuizFragment;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        userMenuLayout = View.inflate(mActivity, R.layout.fragment_user_menu, null);
        fragmentManager = mActivity.getSupportFragmentManager();
        initView();
        return userMenuLayout;
    }

    public void initView() {
        /* 个人信息 */
        userMenuLayout.findViewById(R.id.button_infomation).setOnClickListener(this);
        /* 我的提问 */
        userMenuLayout.findViewById(R.id.button_myQuestion).setOnClickListener(this);
        /* 这里监听的是“我的回答”按钮 */
        userMenuLayout.findViewById(R.id.button_myAnswer).setOnClickListener(this);
        /* 我的投稿 */
        userMenuLayout.findViewById(R.id.button_myDraft).setOnClickListener(this);
        /* 我的征集令 */
        userMenuLayout.findViewById(R.id.button_myCollect).setOnClickListener(this);
        /* 浏览历史 */
        userMenuLayout.findViewById(R.id.button_myHistory).setOnClickListener(this);
        /* 这里监听的是“设置”按钮 */
        userMenuLayout.findViewById(R.id.button_settings).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()) {
        case R.id.button_infomation:
            Toast.makeText(getContext(), getActivity().toString(), Toast.LENGTH_SHORT).show();
            break;
        case R.id.button_myQuestion:
            if(null == myQuizFragment) {
                myQuizFragment = new MyQuizFragment();
            }
            /* 从用户菜单的父fragment（即HomeFragment）跳转到回答Fragment */
            mActivity.showFragment(myQuizFragment);
            break;
        case R.id.button_myAnswer:
            /* 显示MyAnswerFragment */
            if(null == myAnswerFragment) {
                myAnswerFragment = new MyAnswerFragment();
            }
            /* 从用户菜单的父fragment（即HomeFragment）跳转到回答Fragment */
            mActivity.showFragment(myAnswerFragment);
            break;
        case R.id.button_myDraft:
            break;
        case R.id.button_myCollect:
            break;
        case R.id.button_myHistory:
            break;
        case R.id.button_settings:
            break;
        default:
            break;
        }
    }
}
