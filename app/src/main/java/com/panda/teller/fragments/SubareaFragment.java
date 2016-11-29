package com.panda.teller.fragments;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.panda.teller.R;

/**
 * Created by root on 16-11-20.
 */

public class SubareaFragment extends BaseFragment implements View.OnClickListener {

    private View subareaLayout;

    /* 返回按钮 */
    private ImageView undoImage;

    /* 两个按钮用于切换视频列表和问题列表 */
    private Button videoBtn;
    private Button quizBtn;

    /* 两个Fragment用于显示视频/问题列表 */
    private VideoListFragment videoListFragment;
    private QuizListFragment quizListFragment;

    private FragmentManager fragmentManager;

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        subareaLayout = inflater.inflate(R.layout.fragment_subarea, container, false);
        fragmentManager = getFragmentManager();
        initView();
        setTabSelection(0);
        return subareaLayout;
    }

    private void initView() {
        undoImage = (ImageView) subareaLayout.findViewById(R.id.iv_fragment_subarea_undo);
        undoImage.setOnClickListener(this);
        videoBtn = (Button) subareaLayout.findViewById(R.id.btn_fragment_subarea_video);
        videoBtn.setOnClickListener(this);
        quizBtn = (Button) subareaLayout.findViewById(R.id.btn_fragment_subarea_quiz);
        quizBtn.setOnClickListener(this);
    }

    /**
     * 清除按钮的选中状态
     */
    private void clearSelection() {
        videoBtn.setBackgroundResource(R.drawable.grey_btn_normal);
        quizBtn.setBackgroundResource(R.drawable.grey_btn_normal);
    }

    public void setTabSelection(int position) {
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        /* 先隐藏所有Fragment */
        hideFragments(transaction);
        /* 清除按钮的选中状态 */
        clearSelection();
        /* 根据位置决定显示的Fragment */
        switch(position) {
        case 0:
            videoBtn.setBackgroundResource(R.drawable.grey_btn_pressed);
            showFragment(transaction, 0);
            break;
        case 1:
            quizBtn.setBackgroundResource(R.drawable.grey_btn_pressed);
            showFragment(transaction, 1);
            break;
        default:
            break;
        }
        /* 不要忘了提交 */
        transaction.commit();
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()) {
        case R.id.iv_fragment_subarea_undo:
            /* 直接隐藏本Fragment */
            mActivity.hideFragment(this);
            break;
        case R.id.btn_fragment_subarea_video:
            /* 显示视频列表 */
            setTabSelection(0);
            break;
        case R.id.btn_fragment_subarea_quiz:
            /* 显示问题列表 */
            setTabSelection(1);
            break;
        default:
            break;
        }
    }
    /**
     * 隐藏所有Fragment，避免同时出现
     * @param transaction 需要调用方提交事务
     */
    private void hideFragments(FragmentTransaction transaction) {
        if(videoListFragment != null) {
            transaction.hide(videoListFragment);
        }
        if (quizListFragment != null) {
            transaction.hide(quizListFragment);
        }
    }

    /**
     * 切换content内容
     * @param transaction 需要调用方提交事务
     * @param fragmentId 0 for VideoListFragment，1 for QuizListFragment
     */
    private void showFragment(FragmentTransaction transaction, int fragmentId) {
        switch(fragmentId) {
        case 0:
            /* 显示视频列表Framgent */
            if (null == videoListFragment) {
                videoListFragment = new VideoListFragment();
                transaction.add(R.id.fl_framgent_subarea_content, videoListFragment);
            } else {
                transaction.show(videoListFragment);
            }
            break;
        case 1:
            /* 显示问题列表Framgent */
            if(null == quizListFragment) {
                quizListFragment = new QuizListFragment();
                transaction.add(R.id.fl_framgent_subarea_content, quizListFragment);
            } else {
                transaction.show(quizListFragment);
            }
            break;
        default:
            break;
        }
    }
}
