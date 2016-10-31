package com.panda.teller;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by root on 16-10-29.
 */

public class RegrMobileActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.regr_mobile);

        // 验证码按钮
        Button codeBtn = (Button) findViewById(R.id.btn_regr_code);
        codeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 请求验证码
            }
        });

        // 提交按钮
        Button submitBtn = (Button) findViewById(R.id.btn_regr_submit);
        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 1.判断密码框和确认密码框内容是否一致

                // 2.提交表单（密码只提交一份）
                String address =
                HttpUtil.sendHttpGETRequest();
            }
        });

        // 跳转到邮箱注册页面
        TextView toEmailTv = (TextView) findViewById(R.id.btn_regr_toEmail);
        toEmailTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


    }

}
