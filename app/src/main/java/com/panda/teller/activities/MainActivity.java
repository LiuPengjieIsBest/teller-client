package com.panda.teller.activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

/**
 * Created by root on 16-11-1.
 * 主页
 */

public class MainActivity extends Activity {

    public static void actionStart(Context context, String userId) {
        Intent intent = new Intent(context, MainActivity.class);
        intent.putExtra("userId", userId);
        context.startActivity(intent);
    }

}
