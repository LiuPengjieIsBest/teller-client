package com.panda.teller.activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

/**
 * Created by root on 16-10-31.
 * 邮箱注册页面
 * 点击提交后将命令服务器发送邮件，此时邮箱和密码已经登记到了服务器
 * 手机进入一个等待页面，此时默认循环调用服务器的登录api，但是因为未验证所以无正确的返回值
 * 用户点击邮件中的激活链接，此时等待页面消失，进入主页
 *
 * 暂时不考虑邮件验证，我们提交后若收到正确的响应就直接跳转到MainActivity
 */

public class RegrEmailActivity extends Activity {
    /**
     * TODO: 邮箱注册页
     */

    public static void actionStart(Context context) {
        Intent intent = new Intent(context, RegrEmailActivity.class);
        context.startActivity(intent);
    }

}
