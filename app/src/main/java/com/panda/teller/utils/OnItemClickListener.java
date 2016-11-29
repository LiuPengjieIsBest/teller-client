package com.panda.teller.utils;

/**
 * Created by root on 16-11-22.
 */

import android.view.View;

/* 点击后回调并将对应的Bean传入 */
public interface OnItemClickListener {
    void onItemClick(View v, Object o);
}
