package com.panda.teller.utils;

import android.app.Application;
import android.content.Context;

/**
 * 用于在一些工具类中获取Context对象
 */

public class MyApplication extends Application {

    private static Context context;

    @Override
    public void onCreate() {
        /* 获取一个应用程序级别的Context */
        context = getApplicationContext();
    }

    public static Context getContext() {
        return context;
    }

}
