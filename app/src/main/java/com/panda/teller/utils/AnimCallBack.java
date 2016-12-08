package com.panda.teller.utils;

import android.view.MotionEvent;

/**
 * Created by root on 16-12-8.
 */

public interface AnimCallBack {

    boolean solveTouchEvent(MotionEvent event);

    void onAnimEnd();

    void onAnimRepeat();

    void onAnimStart();
}
