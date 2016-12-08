package com.panda.teller.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.panda.teller.R;
import com.panda.teller.utils.AnimCallBack;

/**
 * Created by root on 16-12-4.
 */

public class PlayVideoFragment extends BaseFragment {

    private View layout;

    private View commentContainer;

    private boolean isCommentHide = true;
    private boolean isAnim = false;
    private float lastX = 0;
    private float lastY = 0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        layout = inflater.inflate(R.layout.fragment_playvideo, container, false);
        initView();
        return layout;
    }

    private void initView() {
        commentContainer = layout.findViewById(R.id.ll_fragment_playvideo_comment);
        mActivity.setAnimCallBack(new AnimCallBack() {
            @Override
            public boolean solveTouchEvent(MotionEvent event) {
                if(isAnim) {
                    /* 动画正在进行 */
                    return false;
                }
                final int action = event.getAction();
                float x = event.getX();
                float y = event.getY();

                switch(action) {
                /*case MotionEvent.ACTION_UP:
                    lastX = x;
                    lastY = y;
                    return false; */
                case MotionEvent.ACTION_MOVE:
                    float dx = Math.abs(x - lastX);
                    float dy = Math.abs(y - lastY);
                    boolean down = y > lastY;
                    lastX = x;
                    lastY = y;
                    if(dx < 8 && dy > 8 && isCommentHide && !down) {
                        /* 手势向上滑动，控制显示评论框 */
                        Animation anim = AnimationUtils.loadAnimation(
                                mActivity, R.anim.push_bottom_in);
                        anim.setFillAfter(true);
                        anim.setAnimationListener(mActivity);
                        commentContainer.startAnimation(anim);
                    } else if(dx < 8 && dy > 8 && !isCommentHide && down) {
                        /* 手势向下滑动，隐藏评论框 */
                        Animation anim = AnimationUtils.loadAnimation(
                                mActivity, R.anim.push_bottom_out);
                        anim.setFillAfter(true);
                        anim.setAnimationListener(mActivity);
                        commentContainer.startAnimation(anim);
                    } else {
                        return false;
                    }
                    isCommentHide = !isCommentHide;
                    isAnim = true;
                    break;
                default:
                    return false;
                }
                return false;
            }

            @Override
            public void onAnimEnd() {
                if(isCommentHide) {
                    commentContainer.setVisibility(View.GONE);
                }
                isAnim = false;
            }

            @Override
            public void onAnimRepeat() {
            }

            @Override
            public void onAnimStart() {
                if(!isCommentHide) {
                    commentContainer.setVisibility(View.VISIBLE);
                }
            }
        });
    }



}
